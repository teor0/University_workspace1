package esame1606;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TourSem extends Tour{

	private Random r=new Random();
	private Semaphore mutex=new Semaphore(1,true);
	private Semaphore formato=new Semaphore(0);
	private Semaphore posti;
	private Semaphore terminare=new Semaphore(0);
	private Semaphore inizio=new Semaphore(0);
	private Semaphore fine=new Semaphore(0);
	private int prima;
	private int seconda;
	private int pausa;
	
	public TourSem(int turisti) {
		super(turisti);
		posti=new Semaphore(postiIniziali);
	}

	@Override
	void attendiFormazioneGruppo() throws InterruptedException {
		formato.acquire();
		mutex.acquire();
		prima=stabilisciTempi(MAX_PARTE,MIN_PARTE);
		pausa=stabilisciTempi(MAX_PAU,MIN_PAU);
		seconda=stabilisciTempi(MAX_PARTE,MIN_PARTE);
		System.out.println("il gruppo si e' formato adesso si attende 15s");
		attesaGruppo();
		inizio.release();
		mutex.release();
	}

	@Override
	void visitaInizia() throws InterruptedException {
		inizio.acquire();
		mutex.acquire();
		System.out.println("inizia visita");
		attesaParte(prima);
		System.out.println("pausa");
		attesaParte(pausa);
		System.out.println("seconda parte");
		attesaParte(seconda);
		terminare.release();
		mutex.release();
	}

	@Override
	void visitaFine() throws InterruptedException {
		terminare.acquire();
		mutex.acquire();
		System.out.println("Visita terminata");
		fine.release(postiIniziali);
		mutex.release();
	}

	@Override
	void turistaInizia() throws InterruptedException {
		posti.acquire();
		mutex.acquire();
		System.out.format("Turista %d, si unisce al gruppo %n", Thread.currentThread().getId());
		postiLiberi--;
		if(postiLiberi==0)
			formato.release();
		mutex.release();
	}

	@Override
	void turistaFine() throws InterruptedException {
		fine.acquire();
		mutex.acquire();
		posti.release();
		postiLiberi++;
		System.out.format("Turista %d, lascia il gruppo %n", Thread.currentThread().getId());
		mutex.release();
	}
	
	private void attesaGruppo() throws InterruptedException{
		TimeUnit.SECONDS.sleep(ATTESA_GRUPPO);
	}
	
	private void attesaParte(int x) throws InterruptedException{
		TimeUnit.SECONDS.sleep(x);
	}
	
	private int stabilisciTempi(int max, int min){
		return r.nextInt(max-min+1)+min;
	}
	
	public static void main(String[] args) {
		TourSem ts=new TourSem(20);
		ts.test(60);
	}
	
}
