import java.util.concurrent.Semaphore;

class CyclicBarrier {
    private int threshold;
    private int count = 0;
    private Semaphore barrier1 = new Semaphore(0);
    private Semaphore barrier2 = new Semaphore(1);

    public CyclicBarrier(int threshold){
        this.threshold = threshold;
    }

    public void await(){
        synchronized(this){
            count++;
            if(count == threshold){
                try{
                    barrier2.acquire();
                }catch(InterruptedException e){}
                barrier1.release();
            }
        }
        try{
            barrier1.acquire();
        }catch(InterruptedException e){}
        barrier1.release();
        synchronized(this){
            count--;
            if(count == 0){
                try{
                    barrier1.acquire();
                }catch(InterruptedException e){}
                barrier2.release();
            }
        }
        try{
            barrier2.acquire();
        }catch(InterruptedException e){}
        barrier2.release();
    }
}

