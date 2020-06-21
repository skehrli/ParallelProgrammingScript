class Test{
    public static void main(String[] args){
        Thread A = new Thr(1);
        Thread B = new Thr(2);
        A.start();
        B.start();
    }
}

class Thr extends Thread {
    static Object a = new Object();
    static Object b = new Object();
    int id;
    public Thr(int id){this.id = id;}
    public void run(){
        if(id == 2)
            try{
                sleep(1000);
            }catch(InterruptedException e){}
        System.out.printf("Thread %d is atttempting to acquire lock a\n", id);
        synchronized(a){
            System.out.printf("Thread %d is atttempting to acquire lock b\n", id);
            synchronized(b){
                try{
                    b.wait();
                }catch(InterruptedException e){}
            }
        }
    }
}