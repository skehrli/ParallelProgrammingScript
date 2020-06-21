

public class SynchronizedCounter extends Thread {
    static Object lock = new Object();
    boolean choice;

    static Integer cnt = 0;

    public void add(){
        synchronized(cnt){
            while(cnt % 2 == 1){
                try{
                    cnt.wait();
                } catch(InterruptedException e){}
            }
            cnt++;
            cnt.notifyAll();
        }
    }

    public void subtract(){
        synchronized(cnt){
            while(cnt % 2 == 0){
                try{
                    cnt.wait();
                } catch(InterruptedException e){}
            }
            cnt--;
            cnt.notifyAll();
        }
    }

    public SynchronizedCounter(boolean choice){this.choice = choice;}

    public void run(){
        if(choice){
            for(int i = 0; i < 1000; i++){
                add();
            }
            for(int i = 0; i < 1000; i++){
                subtract();
            }
        } else {
            for(int i = 0; i < 1000; i++){
                subtract();
            }
            for(int i = 0; i < 1000; i++){
                add();
            }
        }
    }


    public static void main(String[] args){
        Thread t1 = new SynchronizedCounter(true);
        Thread t2 = new SynchronizedCounter(true);
        Thread t3 = new SynchronizedCounter(false);
        Thread t4 = new SynchronizedCounter(false);
        t1.start(); t2.start(); t3.start(); t4.start();
        try{
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch(InterruptedException e){}
        System.out.println(SynchronizedCounter.cnt);
    }


}

