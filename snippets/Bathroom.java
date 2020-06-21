import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bathroom extends Thread{
    private volatile static int men = 0;
    private volatile static int women = 0;
    private static Object lock = new Object();
    private static Lock l = new ReentrantLock();
    private static Condition menQueue = l.newCondition();
    private static Condition womenQueue = l.newCondition();

    public void printinfo(){
        System.out.printf("=====================\n\tmen: %d \n\twomen: %d\n",men,women);
        System.out.flush();
    }

    public void femaleEnter() {
        l.lock();
        while(men > 0 || women > 2){
            System.out.println("Female waiting");
            printinfo();
            try{
                womenQueue.await();
            } catch(InterruptedException e){}
        }
        women++;
        System.out.println("Female entered");
        l.unlock();
    }

    public void femaleExit() {
        l.lock();
        women--;
        if(women == 0)
            menQueue.signalAll();
        else
            womenQueue.signal();
        System.out.println("Female left");
        l.unlock();
    }

    public void maleEnter() {
        l.lock();
        while(women > 0 || men > 2){
            System.out.println("Male waiting");
            printinfo();
            try{
                menQueue.await();
            }catch(InterruptedException e){}
        }
        men++;
        System.out.println("Male entered");
        l.unlock();
    }

    public void maleExit() {
        l.lock();
        men--;
        if(men == 0)
            womenQueue.signalAll();
        else
            menQueue.signal();
        System.out.println("Male left");
        l.unlock();
    }

    public void run(){
        Random rand = new Random();
        int gender;
        for(int i = 0; i < 50; i++){
            gender = rand.nextInt(2);
            if(gender == 0){
                femaleEnter();
                try{
                    sleep(10);
                } catch(InterruptedException e){}
                femaleExit();
            } else {
                maleEnter();
                try{
                    sleep(10);
                } catch(InterruptedException e){}
                maleExit();
            }
        }
    }

    public static void main(String[] args){
        Thread[] arr = new Thread[50];
        for(int i = 0; i < 50; i++){
            arr[i] = new Bathroom();
            arr[i].start();
        }
        for(int i = 0;  i < 50; i++){
            try{
                arr[i].join();
            } catch(InterruptedException e){}
        }
    }
}