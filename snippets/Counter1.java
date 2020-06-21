class Counter1 implements Counter{
    int cnt = 0;
    public void incr(){
        cnt++;
    }
    public int get(){return cnt;}
}

class CountThread extends Thread{
    Counter cntr;

    public CountThread(Counter cntr){
        this.cntr = cntr;
    }
    
    public void run(){
        for(int i = 0; i<1000; i++){
            cntr.incr();
        }
    }
}

class Counter2 implements Counter{
    int cnt = 0;
    public synchronized void incr(){
        cnt++;
    }
    public int get(){return cnt;}
}

interface Counter {
    public void incr();
    public int get();
}

class Main {
    public static void main(String[] args){
        Counter cntr1 = new Counter1();
        Counter cntr2 = new Counter2();
        Thread t1 = new CountThread(cntr1);
        Thread t2 = new CountThread(cntr1);
        t1.start(); t2.start();
        try{
            t1.join();
            t2.join();
        }catch(InterruptedException e){}
        System.out.println(cntr1.get());

        t1 = new CountThread(cntr2);
        t2 = new CountThread(cntr2);
        t1.start(); t2.start();
        try{
            t1.join();
            t2.join();
        }catch(InterruptedException e){}
        System.out.println(cntr2.get());
    }
}