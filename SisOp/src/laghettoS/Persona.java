package laghettoS;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Persona extends Thread{
	
	protected final int[] pausa= {1,3};
	protected final int[] MIN_AZIONE= {200, 300};
	protected final int[] MAX_AZIONE= {600, 800};
	protected final int tipo;
	private Random r=new Random();
	private Laghetto l;
	
	public Persona(int t, Laghetto l) {
		this.l=l;
		tipo=t;
	}
	
	
	public void run() {
		while(true) {
			try {
				l.inizia(tipo);
				azione();
				l.finisci(tipo);
				riposa();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void azione() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(r.nextInt((MAX_AZIONE[tipo]-MIN_AZIONE[tipo]+1))+MIN_AZIONE[tipo]);
	}
	
	private void riposa() throws InterruptedException {
		TimeUnit.SECONDS.sleep(pausa[tipo]);
	}

}
