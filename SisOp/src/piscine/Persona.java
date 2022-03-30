package piscine;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Persona extends Thread{

	private final int MIN_TUFFO=3;
	private final int MAX_TUFFO=6;
	private final int DOCCIA=2;
	private Random r=new Random();
	private Piscina p;
	
	
	public Persona(Piscina p){
		this.p=p;
	}
	
	public void run() {
		try {
			int corsia=r.nextInt(p.corsie);
			p.scegliCorsia(corsia);
			tuffo(MAX_TUFFO,MIN_TUFFO);
			p.esci(this);
			TimeUnit.SECONDS.sleep(DOCCIA);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void tuffo(int max, int min) throws InterruptedException {
		TimeUnit.SECONDS.sleep(r.nextInt(max-min+1)+min);
	}
	
}
