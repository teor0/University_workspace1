package casello;

import java.util.LinkedList;
import java.util.concurrent.locks.*;

public class CaselloLock extends Casello{

	private Lock l=new ReentrantLock();
	private Condition[] porta;
	private LinkedList<Thread>[] fila;
	
	public CaselloLock(int porte, int tariffa){
		super(porte,tariffa);
		porta=new Condition[porte];
		fila=new LinkedList[porte];
		for(int i=0; i<porte; i++) {
			porta[i]=l.newCondition();
			fila[i]=new LinkedList<Thread>();
		}
		System.out.println(this);
	}
	
	@Override
	public String toString() {
		String ret = "Casello: ";
		for (int i = 0; i < fila.length; i++) {
			ret+=i+":[";
			for (Thread t:fila[i]) {
				ret+=t.getId()+" ";
			}
			ret = ret.trim();
			ret+="] ";
		}
		
		ret+="incasso: "+incasso;
		
		return ret;
	}
	
	
	
	@Override
	public void paga(int nPorta, int km) throws InterruptedException {
		Thread t=Thread.currentThread();
		l.lock();
		try {
			incasso+=km*tariffa;
			fila[nPorta].remove(t);
			if(!fila[nPorta].isEmpty())
				porta[nPorta].signalAll();
		//puo' essere messo anche senza if in quanto signalAll non ha effette se non c'e' nessuno in coda
			System.out.format("Veicolo %d paga %d alla porta %d ed esce %n", t.getId(),km*tariffa, nPorta);
			System.out.println(this);
		} finally {
			l.unlock();
		}
		
	}

	@Override
	public void scegliPorta(int nPorta) throws InterruptedException {
		Thread t=Thread.currentThread();
		//veicolo t
		l.lock();
		try {
			fila[nPorta].add(t);
			while(!mioTurno(t,nPorta)) {
				porta[nPorta].await();
			}
			System.out.format("Veicolo %d, entra nella porta %d %n",t.getId(), nPorta);
			System.out.println(this);
		} finally {
			l.unlock();
		}
		
	}
	
	private boolean mioTurno(Thread t, int nPorta) {
		return fila[nPorta].getFirst()==t;		
	}

	public static void main(String[] args) {
		int N=5, V=10, T=10;
		CaselloLock c=new CaselloLock(N,T);
		c.test(V);
	}
	
	
	
}
