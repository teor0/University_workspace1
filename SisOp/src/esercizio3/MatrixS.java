package esercizio3;
import java.util.concurrent.atomic.AtomicInteger;
public class MatrixS extends Matrix {

	private AtomicInteger[][] matrix;
	
	public MatrixS(int[][]m) {
		super(m);
		AtomicInteger[][] matrice=new AtomicInteger[m.length][m[0].length];
		for(int i=0; i<matrice.length; i++)
			for(int j=0; j<matrice[0].length; j++)
				matrice[i][j]=new AtomicInteger(m[i][j]);
		matrix=matrice;
	}
	
	@Override
	public void incrementa(int colonna) throws InterruptedException {
		for(int i=0; i<matrix[0].length; i++)
			matrix[i][colonna].addAndGet(1);
	}

	@Override
	public void decrementa(int riga) throws InterruptedException {
		int s=-1;
		for(int i=0; i<matrix.length; i++)
			matrix[riga][i].addAndGet(s);
	}

}
