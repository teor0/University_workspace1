package produttoreconsumatore;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferLockID extends Buffer{

	private class OrdinaPro implements Comparator<Thread>{
		@Override
		public int compare(Thread p1, Thread p2) {
			if(p1.getId()< p2.getId())
				return 1;
			else if(p1.getId()> p2.getId())
				return -1;
			return 0;
		}
	}
	
	private class OrdinaCon implements Comparator<Thread>{
		@Override
		public int compare(Thread p1, Thread p2) {
			if(p1.getId()< p2.getId())
				return 1;
			else if(p1.getId()> p2.getId())
				return -1;
			return 0;
		}
	}
	
	
	private PriorityQueue<Thread> codaProduttori=new PriorityQueue<>(new OrdinaPro());
	private PriorityQueue<Thread> codaConsumatori=new PriorityQueue<>(new OrdinaCon());
	protected int nEl=0;
	private Lock l=new ReentrantLock();
	private Condition bufferPieno=l.newCondition();
	private Condition bufferVuoto=l.newCondition();
	
	public BufferLockID(int dimensione) {
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
		return nEl<buffer.length && Thread.currentThread()== codaProduttori.peek(); 
	}
	
	private boolean possoPrendere() {
		return nEl>0 && Thread.currentThread()==codaConsumatori.peek();
	}
	
	public static void main(String[] args) {
		BufferLockID b=new BufferLockID(10);
		b.test(5, 5);
	}
}
