package esame1606;

public class Turista extends Thread{
	
	private Tour t;

	public Turista(Tour t) {
		this.t=t;
	}
	
	public void run() {
		try {
			t.turistaInizia();
			t.turistaFine();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	

}