package aziendaAgricola;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Cliente extends Thread{

	private int ID;
	private Azienda a;
	private Random r=new Random();
	private int MAX_SACCHI=10, MIN_SACCHI=1;
	private int SPOSTA_SACCHI=1;
	private int sacchi;
	private int ATTESA_MIN=3, ATTESA_MAX=9;
	
	public Cliente(int id, Azienda a) {
		this.a=a;
		ID=id;
	}
	
	public void run() {
		sacchi=r.nextInt((MAX_SACCHI-MIN_SACCHI+1)+MIN_SACCHI);
		while(true) {
			try {
				a.paga(sacchi);
				attesa();
				System.out.format("Cliente %d, acquista %d sacchi %n",ID,sacchi);
				for(int i=0; i<sacchi; i++) {
					a.ritira();
					TimeUnit.SECONDS.sleep(SPOSTA_SACCHI);
					sacchi--;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void attesa() throws InterruptedException {
		TimeUnit.SECONDS.sleep(r.nextInt((ATTESA_MAX-ATTESA_MIN+1)+ATTESA_MIN));
	}

}
