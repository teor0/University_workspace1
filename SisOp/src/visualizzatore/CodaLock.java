package visualizzatore;

import java.util.concurrent.locks.*;

public class CodaLock extends Coda {

	private Lock l=new ReentrantLock();
	private Condition inserisci=l.newCondition();
	private Condition preleva=l.newCondition();
	
	public CodaLock(int capienza) {
		super(capienza);
	}
	
	
	@Override
	public void inserisci(String[] stringhe){
		l.lock();
		try {
			while(!possoInserire(stringhe.length)) {
				inserisci.await();
			}
			for(int i=0; i<stringhe.length; i++)
				lista.add(stringhe[i]);
			preleva.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			l.unlock();
		}
		
	}

	@Override
	public String visualizza() {
		String ret=null;
		l.lock();
		try {
			while(!possoPrelevare()) {
				preleva.await();
			}
			ret=lista.removeFirst();
			inserisci.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			l.unlock();
		}
		return ret;
		
	}
	
	private boolean possoInserire(int x) {
		return lista.size()+x <=capienza;
	}
	
	private boolean possoPrelevare() {
		return lista.size()>0;
	}
	
	public static void main(String[] args) {
		CodaLock c=new CodaLock(100);
		c.test(10);
	}

}
