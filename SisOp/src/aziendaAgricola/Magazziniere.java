package aziendaAgricola;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Magazziniere extends Thread{

	private Azienda a;
	private Random r=new Random();
	private final int ATTESA_MIN=6, ATTESA_MAX=12;
	
	public Magazziniere(Azienda a) {
		this.a=a;
	}
	
	public void run() {
		while(true) {
			try {
				a.caricaSacchi();
				attesa();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void attesa() throws InterruptedException {
		TimeUnit.SECONDS.sleep(r.nextInt((ATTESA_MAX-ATTESA_MIN+1)+ATTESA_MIN));
	}
	
	public String toString(){
		return "Magazziniere ha caricato "+a.sacchettiIniziali+ " sacchi nel magazzino";
	}
	
}
