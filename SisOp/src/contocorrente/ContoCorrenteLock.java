package contocorrente;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ContoCorrenteLock extends ContoCorrente {

	private Lock l=new ReentrantLock();
	
	public ContoCorrenteLock(int deposito) {
		super(deposito);
	}
		
	@Override
	public void deposita(int importo) {
		l.lock();
		try {
			deposito+=importo;
		}finally {
			l.unlock();
		}
	}

	@Override
	public void ritira(int importo) {
		l.lock();
		try {
			deposito-=importo;
		}finally {
			l.unlock();
		}
	}

}
