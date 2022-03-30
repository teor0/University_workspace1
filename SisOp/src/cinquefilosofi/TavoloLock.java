package cinquefilosofi;

import java.util.concurrent.locks.*;

public class TavoloLock extends Tavolo{

	private Lock l=new ReentrantLock();
	private Condition[] bacchettaLibera=new Condition[NUM_FILOSOFI];
	
	public TavoloLock() {
		for(int i=0; i<NUM_FILOSOFI; i++)
			bacchettaLibera[i]=l.newCondition();
	}
	
	@Override
	public void prendiBacchette(int i) throws InterruptedException {
		l.lock();
		try {
			while(bacchette[i] || bacchette[(i+1)%NUM_FILOSOFI]) {
				if(bacchette[i])
					bacchettaLibera[i].await();
				else 
					bacchettaLibera[(i+1)%NUM_FILOSOFI].await();
			}//while
			bacchette[i]=true;
			bacchette[(i+1)%NUM_FILOSOFI]=true;
		} finally {
			l.unlock();
		}
		
	}

	@Override
	public void rilasciaBacchette(int i) {
		l.lock();
		try {
			bacchette[i]=false;
			bacchette[(i+1)%NUM_FILOSOFI]=false;
			bacchettaLibera[i].signal();
			bacchettaLibera[(i+1)%NUM_FILOSOFI].signal();
		}
		finally {
			l.unlock();
		}
	}

	public static void main(String[] args) {
		TavoloLock tl=new TavoloLock();
		tl.test();
	}
	
	
	
}
