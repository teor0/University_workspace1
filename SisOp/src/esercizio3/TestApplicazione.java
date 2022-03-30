package esercizio3;

import java.util.concurrent.atomic.AtomicInteger;

public class TestApplicazione {

	public static void main(String[] args) throws InterruptedException {
		int rig=150;
		int col=150;
		int[][] matrice = new int[rig][col];

		for (int i = 0; i < matrice.length; i++) {
			for (int j = 0; j < matrice[i].length; j++) {
				matrice[i][j] = 0;
			}
		}
		
		//Matrix m=new MatrixNS(matrice);
		//Matrix m=new MatrixS(matrice);
		Matrix m=new MatrixSemaphore(matrice);
		ThreadIncrementa[] tIn= new ThreadIncrementa[col];
		ThreadDecrementa[] tDe= new ThreadDecrementa[rig];
		
		for(int i=0; i<col; i++) {
			tIn[i]=new ThreadIncrementa(m,i);
			tIn[i].start();
			tDe[i]=new ThreadDecrementa(m,i);
			tDe[i].start();
		}
		
		
		
		for(int i=0; i<col; i++) {
			try {
				tIn[i].join();
				tDe[i].join();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < matrice.length; i++) {
			for (int j = 0; j < matrice[i].length; j++) {
				if (matrice[i][j] != 0) {
					System.out.println();
					System.out.println("Errore.");
					return;
				}
				else {
					System.out.print(matrice[i][j]+ " ");
				}
			}
			System.out.println("\n");
		}
		System.out.println("perfetto");
	}


}
