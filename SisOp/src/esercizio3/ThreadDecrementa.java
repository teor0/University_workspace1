package esercizio3;

public class ThreadDecrementa extends Thread{

	private Matrix m;
	private int riga;
	
	public ThreadDecrementa(Matrix m, int riga) {
		this.m=m;
		this.riga=riga;
	}
	
	public void run() {
		try {
			for(int i=0; i<200; i++)
				m.decrementa(riga);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
