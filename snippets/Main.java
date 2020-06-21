public class Main {
    public static double randAvg(int len){
        double retAvg;
        MPI.Init();
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int[] arr;
        if(rank == 0){
            Random r = new Random();
            arr = new int[len];
            for(int i = 0; i < len; i++){
                arr[i] = r.nextInt();
            }
        }
        int[] buf = new int[len/size];
        MPI.COMM_WORLD.Scatter(arr,0,len/size,MPI.INT,buf,0,len/size,MPI.INT,0);
        double avg = 0;
        for(int i = 0; i < len/size; i++){
            avg += buf[i];
        }
        avg /= buf.length;
        double[] avgbuf = new double[]{avg};
        MPI.COMM_WORLD.Reduce(0,avgbuf,avgbuf,0,MPI.DOUBLE,MPI.SUM,0);
        if(rank == 0)
            retAvg = avgbuf[0]/size;
        MPI.Finalize();
        System.out.println(avgbuf[0]);
        return retAvg;
    }
}