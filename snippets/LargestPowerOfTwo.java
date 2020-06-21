import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class LargestPowerOfTwo {

    // Implement using Fork Join

    public static int find(int[] arr, int lo, int hi){
        int max = 1;
        for(int i = lo; i <= hi; i++){
            int k = 1;
            while(k < arr[i]){
                k *= 2;
                if(k == arr[i] && arr[i] > max)
                    max = arr[i];
            }
        }
        return max;
    }
}