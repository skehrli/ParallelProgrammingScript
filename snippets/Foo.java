import java.util.Random;

public class Foo{


    
    public void randAvg(int len){
        // We assume MPI.Init() has already been called
        int size = MPI.COMM_WORLD.Size();
        int rank = MPI.COMM_WORLD.Rank();
        int[] arr = new int[len];
        if(rank == 0){
            Random r = new Random();
            for(int i = 0; i < len; i++){
                arr[i] = r.nextInt();
            }
        }
        int partSize = arr.length / size;
        // Create a buffer that will hold a subset of the random numbers
        int[] subArr = new int[partSize];
        // Scatter the random numbers to all processes
        MPI.COMM_WORLD.Scatter(arr,0,partSize,MPI.INT,subArr,0,partSize,MPI.INT,0);
        // Compute the average of your subset
        int sum = 0;
        for(int i = 0; i < partSize; i++){
            sum += subArr[i];
        }
        double[] avg = new double[]{(double)sum / partSize};
        // Gather all partial averages down to the root process
        int[] avgs;
        if(rank == 0)
            avgs = new int[size];
        MPI.COMM_WORLD.Gather(avg,0,1,MPI.DOUBLE,avgs,0,1,MPI.DOUBLE,0);
        // Compute the total average of all numbers
        if(rank == 0){
            sum = 0;
            for(int i = 0; i < size; i++){
                sum += avgs[i];
            }
            double average = (double)sum / size;
        }
        // We assume MPI.Finalize() will be called
    }



}