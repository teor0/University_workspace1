package produttoreconsumatore;

import java.util.concurrent.locks.*;

public class BufferLock extends Buffer{

	protected int nEl=0;
	private Lock l=new ReentrantLock();
	private Condition bufferPieno=l.newCondition();
	private Condition bufferVuoto=l.newCondition();
	
	public BufferLock(int dimensione) {
		super(dimensione);
	}
	
	@Override
	public void put(Elemento i) throws InterruptedException {
		l.lock();
		try {
			while(nEl==buffer.length) {
				bufferPieno.await();
			}
			buffer[in]=i;
			in=(in+1)%buffer.length;
			nEl++;
			bufferVuoto.signal();
		} finally {
			l.unlock();
		}
	}

	@Override
	public Elemento get() throws InterruptedException {
		Elemento item;
		l.lock();
		try {
			while(nEl==0) {
				bufferVuoto.await();
			}
			item=buffer[out];
			out=(out+1)%buffer.length;
			nEl--;
			bufferPieno.signal();
		} finally {
			l.unlock();
		}
		return item;
	}

}
