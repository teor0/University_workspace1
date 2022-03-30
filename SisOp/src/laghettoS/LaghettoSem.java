package laghettoS;

import java.util.concurrent.Semaphore;

public class LaghettoSem extends Laghetto{

	private Semaphore mutex=new Semaphore(1);
	private Semaphore pes[]=new Semaphore[2];
	
	public LaghettoSem(int max, int min) {
		super(max,min);
		pes[PESCATORE]=new Semaphore(pesci-min);
		pes[ADDETTO]=new Semaphore(max-pesci);
	}
	
	
	@Override
	public void inizia(int t) {
		try {
			pes[t].acquire(t*(IMMETTI_PESCI-1)+1);
			mutex.acquire();
			System.out.format("voglio %s %n", t==0? "pescare":"liberare");
			mutex.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void finisci(int t) {
		try {
			mutex.acquire();
			agisci(t);
			System.out.println();
			pesciAttuali();
			System.out.println();
			mutex.release();
			pes[1-t].release(t*(IMMETTI_PESCI-1)+1);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void agisci(int t) {
		if(t==PESCATORE) {
			pesci--;
		}
		else if(t==ADDETTO)
			pesci+=10;
	}
	
	public static void main(String[] args) {
		LaghettoSem l=new LaghettoSem(200,50);
		l.pesciAttuali();
		System.out.println();
		l.test(40, 5);
	}

}
