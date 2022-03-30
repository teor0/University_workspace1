package piscine;

import java.util.concurrent.TimeUnit;

public class Istruttore extends Thread{

	private Piscina p;
	private final int PRIMO=8;
	private final int SECONDO=10;
	private final int PAUSA=3;
	
	public Istruttore(Piscina p) {
		this.p=p;
	}
	
	public void run() {
		while(true) {
			try {
				p.apri();
				TimeUnit.SECONDS.sleep(PRIMO);
				p.chiudi();
				p.cacciaFuori();
				TimeUnit.SECONDS.sleep(PAUSA);
				p.apri();
				TimeUnit.SECONDS.sleep(SECONDO);
				p.chiudi();
				p.cacciaFuori();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
