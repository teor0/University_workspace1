package barMod;

import java.text.Format;
import java.util.concurrent.Semaphore;

public class BarSem extends Bar{
	
	private Semaphore mutex=new Semaphore(1);
	private Semaphore[] fila=new Semaphore[FILE];
	protected int[] nClientiFila=new int[FILE];
	
	
	public BarSem() {
		super();
		for(int i=0; i<FILE; i++) {
			fila[i]=new Semaphore(MAX_IN_FILA[i],true);
			nClientiFila[i]=0;
		}
		System.out.println(this);
	}

	@Override
	public int scegli() throws InterruptedException {
		int s=-1;
		mutex.acquire();
		if(postiLiberi[CASSA]>0)
			s=CASSA;
		else if(postiLiberi[BANCONE]>0)
			s=BANCONE;
		else if(nClientiFila[CASSA]<=nClientiFila[BANCONE])
			s=CASSA;
		else
			s=BANCONE;
		System.out.format("Cliente[%s] va verso %s %n",Thread.currentThread().getName(), 
				(s==CASSA? "Cassa": "Bancone"));
		mutex.release();
		return s;
	}

	@Override
	public void inizia(int i) throws InterruptedException {
		mutex.acquire();
		nClientiFila[i]++;
		mutex.release();
		
		fila[i].acquire();
		mutex.acquire();
		nClientiFila[i]--;
		postiLiberi[i]--;
		System.out.format("Cliente[%s] si trova in %s %n",Thread.currentThread().getName(),
				(i==CASSA? "Cassa": "Bancone"));
		System.out.println(this);
		mutex.release();
	}

	@Override
	public void finisci(int i) throws InterruptedException {
		mutex.acquire();
		postiLiberi[i]++;
		fila[i].release();
		System.out.format("Cliente[%s] esce %s %n",Thread.currentThread().getName(),
				(i==CASSA? " dalla cassa": "dal bancone"));
		System.out.println(this);
		mutex.release();
	}
	
	public String toString() {
		String cassa = postiLiberi[CASSA] == 0 ? "X" : " ";
		String bancone = "";
		for (int i = 0; i < MAX_IN_FILA[BANCONE]; i++) {
			if (i < MAX_IN_FILA[BANCONE] - postiLiberi[BANCONE])
				bancone += "X";
			else
				bancone += " ";
		}
		String ret = String.format("CASSA[%s](%d) BANCONE[%s](%d)", cassa,
				nClientiFila[CASSA], bancone, nClientiFila[BANCONE]);
		return ret;
	}
	
	public static void main(String[] args) throws InterruptedException {
		int p=20;
		BarSem b=new BarSem();
		b.test(p);
	}
	

}
