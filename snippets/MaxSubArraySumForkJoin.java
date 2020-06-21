import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class MaxSubArraySumForkJoin extends MaxSubArraySum{

	private static final long serialVersionUID = 1L;

    public MaxSubArraySumForkJoin(int start, int length, int[] input, int CUTOFF) {
		super(start, length, input, CUTOFF);
    }
    
    public Integer compute(){
        // Check base case
        if(length <= CUTOFF){
            return MaxSumHelper.MaximumSum(input, start, start+length-1);
        }
        // Split work
        int mid = length / 2;
        MaxSubArraySumForkJoin l = new MaxSubArraySumForkJoin(start, mid, input, CUTOFF);
        MaxSubArraySumForkJoin r = new MaxSubArraySumForkJoin(start+mid, length-mid, input, CUTOFF);
        // Compute maximum subarray sums of left and right partitions
        l.fork();
        int rightMax = r.compute();
        // Find maximum subarray sum crossing middle border
        int rightMidMax = 0, sum = 0;
        for(int i = start+mid; i<start+length; i++){
            sum += input[i];
            if(sum > rightMidMax)
                rightMidMax = sum;
        }
        int leftMidMax = 0;
        sum = 0;
        for(int i = start+mid-1; i>=start; i--){
            sum += input[i];
            if(sum > leftMidMax)
                leftMidMax = sum;
        }
        // Combine results
        return Math.max(leftMidMax + rightMidMax, Math.max(l.join(), rightMax));
    }

    public static void main(String[] args){
        ForkJoinPool fjp = new ForkJoinPool();
        for(int i = 1; i<20000; i*=2){
            for(int j = 1; j<20000; j*=2){
                int[] arr = randArr(j);
                MaxSubArraySumForkJoin top = new MaxSubArraySumForkJoin(0, j, arr, i);
                long startTime = System.nanoTime();
                int val = fjp.invoke(top);
                long endTime = System.nanoTime();
                long elapsedNs = endTime - startTime;
                double elapsedMs = elapsedNs / 1.0e6;
                System.out.printf("For cutoff %d and length %d: %f \n", i, j, elapsedMs);
            }
            System.out.println("============================================");
        }
    }

    public static int[] randArr(int len){
        Random r = new Random();
        int[] arr = new int[len];
        for(int i = 0; i<len; i++){
            arr[i] = r.nextInt();
        }
        return arr;
    }
}

