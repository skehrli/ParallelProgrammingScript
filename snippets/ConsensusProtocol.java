public abstract class ConsensusProtocol<T> {
    protected T[] proposed = (T[]) new Object [N];
    // announce my input value to the other threads
    void propose(T value){
        proposed[TreadID.get()] = value;
    }
    // figure out which thread was first
    abstract public T decide(T value);
}

