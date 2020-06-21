import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// Implement a concurrent counter in round-robin style for n threads using the Condition interface

public class ConditionCounter{
    AtomicInteger cnt = new AtomicInteger(0);

    public void incr(){
        do {
            int a = cnt.get();
            int b = a+1;
        }while(!cnt.compareAndSet(a, b));
    }
}