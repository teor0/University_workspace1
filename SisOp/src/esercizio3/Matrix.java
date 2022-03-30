package esercizio3;

public abstract class Matrix {
	
	protected int[][] matrix;
	
	public Matrix(int [][] m) {
		matrix=m;
	}
	
	public int getRighe() {
		return matrix.length;
	}
	
	public int getColonne() {
		return matrix[0].length;
	}
	
	public abstract void incrementa(int colonna) throws InterruptedException;
	
	public abstract void decrementa(int riga) throws InterruptedException;
	
	public void getMatrix() {
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[0].length; j++)
				System.out.print(matrix[i][j]+ " ");
			System.out.print("\n");
		} 
	}//getMatrix
	
	
}
