package funiviaS;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;
public class FuniviaLock extends Funivia{

	private Lock l=new ReentrantLock();
	private Condition pieno=l.newCondition();
	private Condition vuoto=l.newCondition();
	private Condition arrivato=l.newCondition();
	private boolean arrivo;
	
	public FuniviaLock(int posti){
		super(posti);
		arrivo=false;
	}
	
	
	@Override
	public void pilotaStart() throws InterruptedException {
		l.lock();
		try {
			while(!possoPartire()) {
				pieno.await();
			}
			System.out.println();
			System.out.println("La funivia parte...");
			TimeUnit.SECONDS.sleep(5);
		} finally {
			l.unlock();
		}
	}




	@Override
	public void pilotaEnd() throws InterruptedException {
		l.lock();
		try {
			for(Turista t:seggiole)
				System.out.format("%d tipo: %d sulla funivia %n", t.getId(), t.getTipo());
			System.out.println("La funivia arriva ed i turisti possono scendere");
			arrivo=true;
			arrivato.signalAll();
		} finally {
			l.unlock();
		}
	}

	@Override
	public void turistaSali(int i) throws InterruptedException {
		Turista t=(Turista)Thread.currentThread();
		l.lock();
		try {
			System.out.format("Turista %d vuole salire %n" , t.getId());
			while(!possoSalire(i)) {
				vuoto.await();
			}
			seggiole.add(t);
			postiLiberi--;
			if(postiLiberi==0) {
				pieno.signal();
			}
		} finally {
			l.unlock();
		}
		
	}


	@Override
	public void turistaScendi(int i) throws InterruptedException {
		l.lock();
		try {
			while(!possoScendere(i)) {
				arrivato.await();
			}
			Turista t=seggiole.remove();
			System.out.format("Turista %d scende %n" ,t.getId());
			postiLiberi++;
			if(postiLiberi==pIniziali) {
				TimeUnit.SECONDS.sleep(2);
				System.out.println("La funivia torna a valle");
				arrivo=false;
				System.out.println();
				vuoto.signalAll();
			}	
		} finally {
			l.unlock();
		}
	}
	
	private boolean possoPartire() {
		return postiLiberi==0;
	}

	private boolean possoSalire(int i) {
		return postiLiberi>0 && i==0;
	}
	
	private boolean possoScendere(int i) {
		return arrivo && i==0;
	}
	
	public static void main(String[] args) {
		FuniviaLock f=new FuniviaLock(15);
		f.test(100);
	}
	

}
