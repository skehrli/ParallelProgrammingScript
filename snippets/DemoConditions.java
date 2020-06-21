
// 1. What's the problem with this implementation and how can we solve it?
// 2. What happens if we initialize number to 2?

public class DemoConditions {
    
    private int number = 1;
    synchronized void enter() {
        while(number <= 0)
            try{
                wait();
            } catch(InterruptedException e){}
        number--;
    }
    synchronized void exit() {
        number++;
        if (number > 0)
            notify();
    }

}