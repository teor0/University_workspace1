package esercizio3;

import java.util.concurrent.Semaphore;

public class MatrixSemaphore extends Matrix{
	
	Semaphore[][] ms;
	
	public MatrixSemaphore(int[][] matrix) {
		super(matrix);
		ms=new Semaphore[matrix.length][matrix[0].length];
		for(int i=0; i<matrix.length; i++)
			for(int j=0; j<matrix[i].length; j++)
				ms[i][j]=new Semaphore(1);
	}
	
	public void decrementa(int riga) throws InterruptedException {
		for(int j=0; j<matrix.length; j++) {
			ms[riga][j].acquire();
			matrix[riga][j]--;
			ms[riga][j].release();
		}
	}
	
	public void incrementa(int colonna)throws InterruptedException{
		for(int i=0; i<matrix[0].length; i++) {
			ms[i][colonna].acquire();
			matrix[i][colonna]++;
			ms[i][colonna].release();
		}
	}
	

}
