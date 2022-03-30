package barMod;
import java.util.concurrent.locks.*;
import java.util.*;
public class BarLock extends Bar{

	private Lock lock = new ReentrantLock(true);
	private Condition[] occupato = new Condition[FILE];
	@SuppressWarnings("unchecked")
	private LinkedList<Thread>[] fila = new LinkedList[FILE];

	public BarLock() {
		super();
		for (int i = 0; i < FILE; i++) {
			occupato[i] = lock.newCondition();
			fila[i] = new LinkedList<Thread>();
		}
		System.out.println(this);
	}

	@Override
	public int scegli() throws InterruptedException {
		int i = -1; // 0

		lock.lock();
		try {
			if (postiLiberi[CASSA] > 0)
				i = CASSA;
			else if (postiLiberi[BANCONE] > 0) {
				i = BANCONE;
			} else if (fila[CASSA].size() <= fila[BANCONE].size()) {
				i = CASSA;
			} else {
				i = BANCONE;
			}
			System.out.format("Persona[%s] vuole andare %s%n", Thread
					.currentThread().getName(), (i == CASSA ? "in CASSA"
					: "al BANCONE"));
		} finally {
			lock.unlock();
		}
		return i;
	}

	public void inizia(int i) throws InterruptedException {
		Thread t = Thread.currentThread();
		lock.lock();
		try {
			fila[i].add(t);

			while (!mioTurno(t, i)) {
				occupato[i].await();
			}

			fila[i].remove(t);
			postiLiberi[i]--; //decremento il numero di posti liberi della i-sima fila
			
			System.out.format("Persona[%s] si trova in %s%n", Thread.currentThread().getName(), 
					(i == CASSA ? "in CASSA": "al BANCONE"));
			System.out.println(this);
		} finally {
			lock.unlock();
		}

	}

	private boolean mioTurno(Thread t, int i) {
		return fila[i].indexOf(t) < postiLiberi[i];
	}

	@Override
	public void finisci(int i) throws InterruptedException {
		lock.lock();
		try {
			postiLiberi[i]++; //incremento il numero di posti liberi della i-sima fila

			if (fila[i].size() > 0) {
				occupato[i].signalAll();
			}

			System.out.format("Persona[%s] è uscita %s%n", Thread.currentThread().getName(), 
					(i == CASSA ? "dalla CASSA": "dal BANCONE"));
			System.out.println(this);
		} finally {
			lock.unlock();
		}
	}
	
	public String toString() {
		String cassa = postiLiberi[CASSA]==0?"X":" ";
		String bancone = "";
		for(int i=0; i<MAX_IN_FILA[BANCONE]; i++){
			if(i<MAX_IN_FILA[BANCONE]-postiLiberi[BANCONE])
				bancone +="X";
			else
				bancone +=" ";
		}
		String tFila[] = new String[FILE];
		for (int i = 0; i < tFila.length; i++) {
			tFila[i] = "";
			for(Thread t: fila[i])
				tFila[i]+=t.getName()+" ";
			tFila[i] = tFila[i].trim();
		}
		
		String ret = String.format("CASSA[%s](%s) BANCONE[%s](%s)",
				cassa, tFila[CASSA],
				bancone, tFila[BANCONE]);
		return ret;
	}
	
	public static void main(String[] args) throws InterruptedException {
		int numPersone = 20;
		new BarLock().test(numPersone);
	}
	
	
	
}
