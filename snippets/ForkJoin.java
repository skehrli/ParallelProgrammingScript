import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class MaxForkJoin extends RecursiveTask<Integer> {
    int l, h;
    int[] arr;

    public MaxForkJoin(int lo, int hi, int[] arr){
        l = lo;
        h = ho;
        this.arr = arr;
    }

    protected Integer compute(){
        // Check base case
        size = h-l;
        if(size == 1)
            return arr [l];
        // Split work
        int mid = size / 2;
        MaxForkJoin m1 = new MaxForkJoin(l, l+mid, arr);
        MaxForkJoin m2 = new MaxForkJoin(l+mid, h, arr);
        // Run subtasks
        m1.fork();
        int max2 = m2.compute();
        int max1 = m1.join();
        // Combine results
        return Math.max(max1, max2);
    }
}

class M{

    public static void main(String[] args){
        int[] arr = new int[]{15,7,9,8,4,22,42,13};
        MaxForkJoin tp = new MaxForkJoin(0, arr.length, arr);
        ForkJoinPool fjp = new ForkJoinPool();
        int res = fjp.invoke(tp);
        System.out.println(res);
    }

}