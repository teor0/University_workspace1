package lettoriscrittori;
import java.util.concurrent.*;

public class MemoriaCondivisaSem extends MemoriaCondivisa {

	private int numLettori = 0;
	private Semaphore mutex = new Semaphore(1, true);
	private Semaphore scrittura = new Semaphore(1, true);
	
	@Override
	public void inizioScrittura() throws InterruptedException {
		scrittura.acquire();
		System.out.format("Il thread %d ha iniziato a scrivere. %n", Thread.currentThread().getId());
	}
	@Override
	public void fineScrittura() throws InterruptedException {
		scrittura.release();
		
	}
	@Override
	public void inizioLettura() throws InterruptedException {
		mutex.acquire();
		if(numLettori==0)
			scrittura.acquire();
		numLettori++;
		System.out.format("Il thread %d ha iniziato a leggere. %n", Thread.currentThread().getId());
		mutex.release();
	}
	@Override
	public void fineLettura() throws InterruptedException {
		mutex.acquire();
		numLettori--;
		if(numLettori==0)
			scrittura.acquire();
		System.out.format("Il thread %d ha terminato a leggere. %n", Thread.currentThread().getId());
		mutex.release();
	}
	
	public static void main(String[] args) {
		MemoriaCondivisa memoria = new MemoriaCondivisaSem();
		memoria.test(10, 4);
	}
}