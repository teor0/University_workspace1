package barMod;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Cliente implements Runnable{

	private static final int[] MIN_ATTESA= {5,20};
	private static final int[] MAX_ATTESA= {10,40};
	private Bar b;
	private Random r=new Random();
	
	public Cliente(Bar b) {
		this.b=b;
	}
	
	public void run(){
		try {
			attesa(0,20);
			int operazione=b.scegli();
			b.inizia(operazione);
			attesa(MAX_ATTESA[operazione],MIN_ATTESA[operazione]);
			b.finisci(operazione);
			operazione=1-operazione;
			b.inizia(operazione);
			attesa(MAX_ATTESA[operazione],MIN_ATTESA[operazione]);
			b.finisci(operazione);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void attesa(int max, int min) throws InterruptedException{
		TimeUnit.SECONDS.sleep(r.nextInt((max-min+1)+min));
	}
	
	
	
}
