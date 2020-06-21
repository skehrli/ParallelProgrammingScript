import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;


public class LargestPowerOfTwoForkJoin extends RecursiveTask<Integer>{
    int lo, hi;
    int[] arr;
    int CUTOFF;

    public LargestPowerOfTwoForkJoin(int[] arr, int lo, int hi, int CUTOFF){
        this.arr = arr;
        this.lo = lo;
        this.hi = hi;
        this.CUTOFF = CUTOFF;
    }

    // Note: Removing the protected keyword here does actually throw an error
    protected Integer compute(){ 
        // 1. Check and possibly deal with base case
        if(hi-lo < CUTOFF){
            return LargestPowerOfTwo.find(arr ,lo ,hi);
        }
        // 2. Split work
        int mid = (hi-lo)/2;
        LargestPowerOfTwoForkJoin left = new LargestPowerOfTwoForkJoin(arr, lo, lo+mid, CUTOFF);
        LargestPowerOfTwoForkJoin right = new LargestPowerOfTwoForkJoin(arr, lo+mid+1, hi, CUTOFF);
        // 3. Start subtasks
        left.fork();
        int r = right.compute();
        // 4. Combine results
        return Math.max(r, left.join());
    }

    // For more accurate results one might run every measurement multiple times and take the average
    public static void main(String[] args){
        // 0. Create ForkJoinPool and invoke top task
        ForkJoinPool fjp = new ForkJoinPool();
        for(int i = 2; i<20000; i*=2){
            for(int j = 1; j<20000; j*=2){
                int[] arr = randArr(j);
                LargestPowerOfTwoForkJoin top = new LargestPowerOfTwoForkJoin(arr, 0, j-1, i);
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
            arr[i] = r.nextInt(100000);
        }
        return arr;
    }
}