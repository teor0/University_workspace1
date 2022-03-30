package produttoreconsumatore;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferLockLimitato extends Buffer{
	
	private ArrayList<Elemento> Buffer;
	private int limite;
	protected int nEl=0;
	private Lock l=new ReentrantLock();
	private Condition bufferPieno=l.newCondition();
	private Condition bufferVuoto=l.newCondition();
	
	public BufferLockLimitato(int limite) {
		super(limite);
		this.limite=limite;
		this.Buffer=new ArrayList<>(limite);
	}
	
	@Override
	public void put(Elemento i) throws InterruptedException {
		l.lock();
		try {
			while(!possoInserire()) {
				bufferPieno.await();
			}
			Buffer.add(in, i);
			in=(in+1)%Buffer.size();
			nEl++;
			bufferVuoto.signalAll();
		} finally {
			l.unlock();
		}
		
	}

	@Override
	public Elemento get() throws InterruptedException {
		l.lock();
		Elemento e=null;
		try {
			while(nEl==0) {
				bufferVuoto.await();
			}
			System.out.println("ele "+nEl);
			e=Buffer.get(out);	
			out=(out+1)%Buffer.size();
			nEl--;
			bufferPieno.signal();
		} finally {
			l.unlock();
		}
		return e;
	}

	private boolean possoInserire() {
		return nEl<limite;
	}
	
	
	public static void main(String[] args) {
		BufferLockLimitato b=new BufferLockLimitato(50);
		b.test(10, 10);
	}
}
