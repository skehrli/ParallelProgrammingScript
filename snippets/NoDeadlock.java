public class NoDeadlock {

    public void work(Object a, Object b){
        boolean done = false;
        while(!done){
            a.acquire();
            if(b.tryAcquire()){
                critical_section();
                b.release();
                done = true;
            }
            a.release();
        }   
    }
    
}

