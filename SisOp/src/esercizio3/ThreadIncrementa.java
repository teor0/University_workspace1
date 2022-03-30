package esercizio3;

public class ThreadIncrementa extends Thread{
	
	private Matrix m;
	private int colonna;
	
	
	public ThreadIncrementa(Matrix m, int colonna) {
		this.m=m;
		this.colonna=colonna;
	}
	
	public void run() {
		try {
			for(int i=0; i<200; i++)
				m.incrementa(colonna);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

}
