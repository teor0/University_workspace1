package lettoriscrittori;

import java.util.concurrent.locks.*;

public class MemoriaCondivisaLock extends MemoriaCondivisa {
	
	private int nLettoriInLettura=0;
	private boolean scrittoreInScrittura=false;
	
	private Lock l=new ReentrantLock();
	private Condition possoScrivere= l.newCondition();
	private Condition possoLeggere=l.newCondition();

	@Override
	public void inizioScrittura() throws InterruptedException {
		l.lock();
		try {
			while(nLettoriInLettura>0 | scrittoreInScrittura) {
				possoScrivere.await();
			}
			scrittoreInScrittura=true;
			System.out.format("Il thread %d ha iniziato a scrivere. %n", Thread.currentThread().getId());
		} finally {
			l.unlock();
		}
	}

	@Override
	public void fineScrittura() throws InterruptedException {
		l.lock();
		try {
			scrittoreInScrittura=false;
			possoLeggere.signalAll();
			possoScrivere.signal();
			System.out.format("Il thread %d ha terminato di scrivere. %n", Thread.currentThread().getId());
		} finally {
			l.unlock();
		}
	}

	@Override
	public void inizioLettura() throws InterruptedException {
		l.lock();
		try {
			while(scrittoreInScrittura) {
				possoLeggere.await();
			}
			nLettoriInLettura++;
			System.out.format("Il thread %d ha iniziato a leggere. %n", Thread.currentThread().getId());
		} finally {
			l.unlock();
		}
	}

	@Override
	public void fineLettura() throws InterruptedException {
		l.lock();
		try {
			nLettoriInLettura--;
			System.out.format("Il thread %d ha terminato a leggere. %n", Thread.currentThread().getId());
			if(nLettoriInLettura==0)
				possoScrivere.signal();
		} finally {
			l.unlock();
		}
	}
	
	public static void main(String[] args) {
		MemoriaCondivisa memoria = new MemoriaCondivisaLock();
		memoria.test(10, 4);
		
	}

	
}
