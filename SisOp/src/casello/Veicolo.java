package casello;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Veicolo extends Thread{

	private final int MIN_KM=5;
	private final int MAX_KM=10;
	private final int T_MAX=6;
	private final int T_MIN=3;
	private final int T_KM=4;
	private Random random=new Random();
	private Casello casello;
	
	public Veicolo(Casello c) {
		casello=c;
	}
	
	public void run(){
		try {
			int km=random.nextInt(MAX_KM-MIN_KM+1)+MIN_KM;
			int t=km*T_KM;
			System.out.format("La macchina %d, impiega %d per arrivare al casello %n",
					Thread.currentThread().getId(), t);
			attendi(t);
			int porta=random.nextInt(casello.porte);
			casello.scegliPorta(porta);
			attendiPagamento(T_MAX,T_MIN);
			casello.paga(porta, km);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void attendiPagamento(int max, int min) throws InterruptedException{
		TimeUnit.SECONDS.sleep(random.nextInt((max-min+1)+min));
	}
	
	private void attendi(int s) throws InterruptedException{
		TimeUnit.SECONDS.sleep(s);
	}
	
}
