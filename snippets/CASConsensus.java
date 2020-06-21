import java.util.concurrent.atomic.AtomicInteger;

public class CASConsensus extends ConsensusProtocol{
    private final int FIRST = -1;
    private AtomicInteger r = new AtomicInteger(FIRST);
    public synchronized Object decide(Object value) {
        propose(value); // Proposed array has size N
        int i = ThreadID.get();
        if (r.compareAndSet(FIRST, i))
            return proposed[i];
        else
            return proposed[r.get()];
    }    
}


