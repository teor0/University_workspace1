package funiviaS;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Turista extends Thread{
	
	private int PIEDI=0, BICI=1;
	private int tipo;
	private Random r=new Random();
	private Funivia f;
	
	public Turista(Funivia f) {
		this.f=f;
		tipo=r.nextInt((BICI-PIEDI+1)+PIEDI);
	}
	
	public void run() {
		while(true) {
			try {
				attesa(6);
				f.turistaSali(getTipo());
				f.turistaScendi(getTipo());
				tipo=1-tipo;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void attesa(int operazione) throws InterruptedException {
		TimeUnit.SECONDS.sleep(operazione);
	}
	
	public int getTipo() {
		return tipo;
	}
	
	

}
