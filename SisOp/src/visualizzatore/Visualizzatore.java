package visualizzatore;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Visualizzatore extends Thread{

	private Coda c;
	private Random r=new Random();
	private final int ATTESA_MIN=2, ATTESA_MAX=5;
	
	public Visualizzatore(Coda c) {
		this.c=c;
	}
	
	public void run() {
		while(true) {
			try {
				attesa();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String daStampare=c.visualizza();
			System.out.println("Visualizzo: "+daStampare);
		}
		
		
	}
	
	private void attesa() throws InterruptedException {
		TimeUnit.SECONDS.sleep(r.nextInt((ATTESA_MAX-ATTESA_MIN+1)+ATTESA_MIN));
	}
	
}
