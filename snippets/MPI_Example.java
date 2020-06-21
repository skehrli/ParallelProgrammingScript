public class MPI_Example {

    
    public static void computePrimeFactors(int[] arr){
        // We assume MPI.Init() has already been called
        int size = MPI.COMM_WORLD.Size();
        int rank = MPI.COMM_WORLD.Rank();
        int partSize = arr.length / size;
        int[] res = new int[partSize];
        for(int i = rank*partSize, j = 0; i < arr.length; i++, j++){
            res[j] = primeFactors(arr[i]);
        }
        MPI.COMM_WORLD.Allgather(res,0,partSize,MPI.INT,arr,0,partSize);
        // We assume MPI.Finalize() will be called
    }


}