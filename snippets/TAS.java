

public class TASConsensusProtocol<T> {
    static int X = 0;
    protected T[] proposed = (T[]) new Object[2];
    // announce my input value to the other threads
    void propose(T value){
        proposed[ThreadID.get()] = value;
    }
    // figure out which thread was first
    public T decide(T value){
        propose(value);
        boolean val = TAS(X);
        if(val) return value;
        else return proposed[1-ThreadID.get()];
    }
}


