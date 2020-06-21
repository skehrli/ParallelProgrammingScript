import java.util.concurrent.Semaphore;

class SimpleBarrier {
    private int threshold;
    private int count = 0;
    private Semaphore barrier = new Semaphore(0);

    public SimpleBarrier(int threshold){
        this.threshold = threshold;
    }

    public void await(){
        synchronized(this){
            count++;
        }
        if(count == threshold) 
            barrier.release();
        try{
            barrier.acquire();
        }catch(InterruptedException e){}
        barrier.release();
    }

    public static void main(String[] args){
        int N = 100;
        SimpleBarrier bar = new SimpleBarrier(N);
        for(int i = 0; i<N; i++){
            final int k = i;
            Thread t = new Thread(new Runnable(){
                public void run(){
                    System.out.printf("Thread %d reached barrier\n",k);
                    bar.await();
                    System.out.printf("Thread %d passed barrier\n",k);
                }
            });
            t.start();
        }
    }
}

