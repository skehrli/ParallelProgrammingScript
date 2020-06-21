

public class RandomConsensus extends ConsensusProtocol{
    private volatile int i = -1;
    public Object decide(Object value){
        propose(value);
        if(i == -1){
            i = ThreadID.get();
            return proposed[ThreadID.get()];
        }
        else {
            return proposed[1-ThreadID.get()];
        }
    }    
}

