package barbiere;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Barbiere extends Thread{

	private Random r=new Random();
	private Sala s;
	private static final int MIN_TAGLIO = 2, MAX_TAGLIO = 6;
	
	public Barbiere(Sala s) {
		this.s=s;
	}
	
	public void run() {
		while(true) {
			try {
				s.tagliaCapelli();
				System.out.println("Taglio in corso...");
				taglia(MAX_TAGLIO, MIN_TAGLIO);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void taglia(int max, int min) throws InterruptedException {
		TimeUnit.SECONDS.sleep(r.nextInt(max-min+1));
	}
	
	
	
}
