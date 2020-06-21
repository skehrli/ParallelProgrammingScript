

public class Useless extends Thread{
    int i;
    Useless(int i){ this.i = i; }
    public void run(){
        System.out.printf("The double value of %d is %d\n",i,2*i);
    }
}

public class M {
    public static void main(String[] args){
        Thread[] threads = new Thread[20];
        for(int i = 0; i < 20; i++){
            Thread t = new Useless(i+1);
            t.start();
            threads[i] = t;
        }
        for(int i= 0; i < 20; i++){
            try{
                threads[i].join();
            } catch(InterruptedException e){}
        }
        System.out.println("All done.");
    }
}


