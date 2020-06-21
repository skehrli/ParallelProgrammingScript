import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public abstract class MaxSubArraySum extends RecursiveTask<Integer> {
    private static final long serialVersionUID = 1L;
    int start;
    int length;
    int[] input;
    int CUTOFF;

    public MaxSubArraySum(int start, int length, int[] input, int CUTOFF){
        this.start = start;
        this.length = length;
        this.input = input;
        this.CUTOFF = CUTOFF;
    }

    public abstract Integer compute();
}