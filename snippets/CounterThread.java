public class CounterThread extends Thread{
    int id;
    ConditionCounter cntr;
    CounterThread(int id, ConditionCounter cntr){
        this.id = id;
        this.cntr = cntr;
    }
    public void run(){
        for(int i = 0; i < 1000; i++){
            cntr.increment(id);
        }
    }

    public static void main(String[] args){
        int N = 100;
        ConditionCounter cntr = new ConditionCounter(N);
        CounterThread[] threads = new CounterThread[N];
        for(int i = 0; i < N; i++){
            threads[i] = new CounterThread(i, cntr);
            threads[i].start();
        }
        for(int i = 0; i < N; i++){
            try{
                threads[i].join();
            } catch(InterruptedException e){}
        }
        System.out.println(cntr.getCnt());
    }
}