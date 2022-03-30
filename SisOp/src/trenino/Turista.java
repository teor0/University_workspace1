package trenino;

public class Turista extends Thread{

	private Trenino t;
	
	public Turista(Trenino t) {
		this.t=t;
	}
	
	public void run() {
		try {
			//while(true) {
				t.turSali();
				t.turScendi();
			//}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}//run
}
