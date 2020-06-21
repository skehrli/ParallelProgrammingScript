import java.util.concurrent.atomic.AtomicBoolean;

public class TATASLock{
    AtomicBoolean state = new AtomicBoolean(false);
    public void lock(){
        do{
            while(state.get()){}
        }while(!state.compareAndSet(false, true));
    }
    public void unlock(){
        state.set(false);
    }
}

