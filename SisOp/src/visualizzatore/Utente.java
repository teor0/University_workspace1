package visualizzatore;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Utente extends Thread {

	private Random r=new Random();
	private final int MIN_STRINGHE=1, MAX_STRINGHE=5;
	private final int ATTESA_MIN=3, ATTESA_MAX=9;
	private int ID;
	private Coda c;
	
	
	public Utente(int id, Coda c) {
		this.c=c;
		ID=id;
	}
	
	public void run() {
		int nS=0;
		while(true) {
			try {
				attesa();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			int nsi=r.nextInt((MAX_STRINGHE-MIN_STRINGHE+1)+MIN_STRINGHE);
			String[] stringheDaInserire=new String[nsi];
			for(int i=0; i<stringheDaInserire.length; i++) {
				stringheDaInserire[i]=new String("S_"+ID+"_"+nS);
				nS++;
			}
			c.inserisci(stringheDaInserire);
			System.out.format("Utente %d ha inserito %d stringhe %n", ID, nS);
		}
	}
	
	private void attesa() throws InterruptedException {
		TimeUnit.SECONDS.sleep(r.nextInt((ATTESA_MAX-ATTESA_MIN+1)+ATTESA_MIN));
	}
	
	
}
