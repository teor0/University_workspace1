package produttoreconsumatore;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferLockFifo extends Buffer{

	private LinkedList<Thread> codaProduttori=new LinkedList<>();
	private LinkedList<Thread> codaConsumatori=new LinkedList<>();
	protected int nEl=0;
	private Lock l=new ReentrantLock();
	private Condition bufferPieno=l.newCondition();
	private Condition bufferVuoto=l.newCondition();
	
	public BufferLockFifo(int dimensione) {
		super(dimensione);
	}
	
	
	@Override
	public void put(Elemento i) throws InterruptedException {
		l.lock();
		try {
			codaProduttori.add(Thread.currentThread());
			while(!possoInserire()) {
				bufferPieno.await();
			}
			codaProduttori.remove();
			buffer[in]=i;
			in=(in+1)%buffer.length;
			nEl++;
			bufferVuoto.signalAll();	
		} finally {
			l.unlock();
		}
	}

	@Override
	public Elemento get() throws InterruptedException {
		Elemento i=null;
		l.lock();
		try {
			codaConsumatori.add(Thread.currentThread());
			while(!possoPrendere()) {
				bufferVuoto.await();
			}
			i=buffer[out];
			out=(out+1)%buffer.length;
			nEl--;
			bufferPieno.signal();
		} finally {
			l.unlock();
		}
		return i;
	}
	
	private boolean possoInserire() {
		return nEl<buffer.length && Thread.currentThread().equals(codaProduttori.getFirst());
	}
	
	private boolean possoPrendere() {
		return nEl>0 && Thread.currentThread().equals(codaConsumatori.getFirst());
	}
}
