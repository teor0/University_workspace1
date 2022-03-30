package piscine;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.concurrent.Semaphore;

public class PiscinaSem extends Piscina{
	
	private Semaphore[] corsia;
	private Semaphore mutex=new Semaphore(1);
	private Semaphore piscina=new Semaphore(1);
	
	public PiscinaSem(int c, int n) {
		super(c,n);
		corsia=new Semaphore[c];
		for(int i=0; i<c; i++)
			corsia[i]=new Semaphore(NUO_PER_CORSIA);
	}

	@Override
	protected int scegliCorsia(int nCorsia) throws InterruptedException {
		corsia[nCorsia].acquire();
		mutex.acquire();
		postiCorsia[nCorsia].add(Thread.currentThread());
		System.out.format("Nuotatore %d entra nella corsia %d %n", Thread.currentThread().getId(),nCorsia);
		System.out.println();
		System.out.println(stampaCorsia(postiCorsia[nCorsia]));
		return nCorsia;
	}

	@Override
	protected void esci(Thread t) throws InterruptedException {
		
	}

	@Override
	protected void apri() throws InterruptedException {
		piscina.release();
	}

	@Override
	protected void chiudi() throws InterruptedException {
		piscina.acquire();
	}
	
	private String stampaCorsia(ArrayList<Thread> a) {
		StringBuilder sb=new StringBuilder(40);
		sb.append("[");
		ListIterator<Thread> l=a.listIterator();
		while(l.hasNext()) {
			sb.append(l.next().getId());
			if(l.hasNext())
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}

}
