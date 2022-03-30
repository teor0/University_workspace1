package esercizio3;

public class MatrixNS extends Matrix{

	public MatrixNS(int[][] m) {
		super(m);
	}
	
	@Override
	public void incrementa(int colonna) throws InterruptedException {
		for(int i=0; i<matrix[0].length; i++)
			matrix[i][colonna]++;
	}

	@Override
	public void decrementa(int riga) throws InterruptedException {
		for(int i=0; i<matrix.length; i++)
			matrix[riga][i]--;
	}

	
	
}
