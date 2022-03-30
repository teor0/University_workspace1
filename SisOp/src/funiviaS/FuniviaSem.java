package funiviaS;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class FuniviaSem extends Funivia{

	private Semaphore mutex=new Semaphore(1);
	private Semaphore pieno;
	private Semaphore sedie;
	private Semaphore arrivo=new Semaphore(0);
	private Semaphore scesa;
	
	public FuniviaSem(int posti) {
		super(posti);
		sedie=new Semaphore(posti);
		scesa=new Semaphore(0);
		pieno=new Semaphore(0);
	}
	
	
	
	@Override
	public void pilotaStart() throws InterruptedException {
		pieno.acquire();
		mutex.acquire();
		System.out.println("La funivia parte...");
		TimeUnit.SECONDS.sleep(5);
		arrivo.release();
		mutex.release();
	}

	@Override
	public void pilotaEnd() throws InterruptedException {
		arrivo.acquire();
		mutex.acquire();
		System.out.println("La funivia arriva ed i turisti possono scendere");
		scesa.release(pIniziali);
		mutex.release();
	}

	@Override
	public void turistaSali(int i) throws InterruptedException{
		Turista t=(Turista)Thread.currentThread();
		if(i==1) {
			System.out.format("Turista %d respinto %n " , t.getId());
			return;
		}
		else {
			sedie.acquire();
			mutex.acquire();
			System.out.format("Turista %d tipo: %d sale %n" , t.getId(),t.getTipo());
			seggiole.add(t);
			postiLiberi--;
			if(postiLiberi==0) {
				pieno.release();
				System.out.println();
			}
			mutex.release();
		}
	}

	@Override
	public void turistaScendi(int i) throws InterruptedException{
		if(i==1)
			return;
		else {
			scesa.acquire();
			mutex.acquire();
			Turista t=seggiole.remove();
			System.out.format("Turista %d tipo: %d scende %n" ,t.getId(), t.getTipo());
			postiLiberi++;
			if(postiLiberi==pIniziali) {
				TimeUnit.SECONDS.sleep(2);
				System.out.println("La funivia torna a valle");
				System.out.println();
				sedie.release(pIniziali);
			}
			mutex.release();
		}
	}
	
	public static void main(String[] args) {
		FuniviaSem f=new FuniviaSem(6);
		f.test(24);
	}

}
