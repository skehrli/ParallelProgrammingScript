package assignment13.MPI;
import mpi.*;
import java.util.Random;

public class HelloWorld {
	
	public static void main(String[] args) {
		waterfall(args);
	}

	public static void waterfall(String[] args){
		Random rnd = new Random();
		MPI.Init(args);
		int size = MPI.COMM_WORLD.Size();
		int rank = MPI.COMM_WORLD.Rank();
		int[] sbuf = new int[1];
		int[] rbuf = new int[1];
		if(size != 1) {
			if(rank == 0) {
				sbuf[0] = rnd.nextInt(1000);
				rbuf[0] = sbuf[0];
				sbuf[0]++;
				System.out.println("On process "+rank+" with buf: "+sbuf[0]);
				MPI.COMM_WORLD.Ssend(sbuf, 0, 1, MPI.INT, rank+1, MPI.ANY_TAG);
			} else if(rank == size-1) {
				MPI.COMM_WORLD.Recv(rbuf, 0, 1, MPI.INT, rank-1, MPI.ANY_TAG);
			} else {
				MPI.COMM_WORLD.Recv(rbuf, 0, 1, MPI.INT, rank-1, MPI.ANY_TAG);
				sbuf[0] = rbuf[0];
				sbuf[0]++;
				MPI.COMM_WORLD.Ssend(sbuf, 0, 1, MPI.INT, rank+1, MPI.ANY_TAG);
			}
		}
		MPI.Finalize();
	}
}
