package barbiere;

import java.util.concurrent.locks.*;

public class SalaLock extends Sala{

	private Lock l=new ReentrantLock();
	private Condition poltrona=l.newCondition();
	private Condition clienteDisponibile=l.newCondition();
	private boolean poltronaLibera=false;
	
	public SalaLock(int sedie) {
		super(sedie);
	}
	
	@Override
	public void tagliaCapelli() throws InterruptedException {
		l.lock();
		try {
			while(numSedie==sedieLibere) {//sala piena niente nuovi clienti
				clienteDisponibile.await();
			}
			poltronaLibera=true;
			poltrona.signalAll();
		} finally {
			l.unlock();
		}
		
	}

	@Override
	public boolean attendiTaglio() throws InterruptedException {
		l.lock();
		Cliente c=(Cliente) Thread.currentThread();
		try {
			if(sedieLibere==0)
				return false;
			sedieLibere--;
			clienteDisponibile.signal();
			while(!poltronaLibera) {
				poltrona.await();
			}
			poltronaLibera=false;
			sedieLibere++;
			return true;
		} finally {
			l.unlock();
		}
	}
	
	public static void main(String[] args) {		
		try {
			Sala s = new SalaLock(5);
			s.test(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
