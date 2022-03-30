package firenze;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TourVar extends Tour{

	private Random r=new Random();
	private Lock l=new ReentrantLock();
	//condition che blocca il turista in quanto il gruppo si e' formato
	private Condition gruppoFormato=l.newCondition();
	//condition che blocca la guida in quanto il gruppo non si e' formato
	private Condition gruppoNonFormato=l.newCondition();
	//condition che blocca la guida finche' non puo' iniziare il tour
	private Condition inizio=l.newCondition();
	//condition che impedisce alla guida di terminare il tour
	private Condition inCorso=l.newCondition();
	//condition che blocca il turista in quanto il tour deve terminare
	private Condition tourFinito=l.newCondition();
	protected boolean attesaConclusa=false;
	protected boolean visitaConclusa=false;
	protected boolean spiega=false;
	private int prima;
	private int seconda;
	private int pausa;
	
	public TourVar(int turisti) {
		super(turisti);
	}

	@Override
	public void attendiFormazioneGruppo() throws InterruptedException {
		l.lock();
		try {
			prima=stabilisciTempi(MAX_PARTE,MIN_PARTE);
			pausa=stabilisciTempi(MAX_PAU,MIN_PAU);
			seconda=stabilisciTempi(MAX_PARTE,MIN_PARTE);
			while(!gruppoFormato()) {
				gruppoNonFormato.await();
			}
			System.out.println("il gruppo si e' formato adesso si attende 15s");
			attesaGruppo();
			attesaConclusa=true;
			inizio.signal();
		} finally {
			l.unlock();
		}
	}


	@Override
	public void visitaInizia() throws InterruptedException {
		l.lock();
		try {
			while(!possoIniziare())
				inizio.await();
			System.out.println("inizia visita");
			attesaParte(prima);
			System.out.println("pausa");
			attesaParte(pausa);
			System.out.println("seconda parte");
			attesaParte(seconda);
			visitaConclusa=true;
			inCorso.signal();
		} finally {
			l.unlock();
		}
	}

	@Override
	public void visitaFine() throws InterruptedException {
		l.lock();
		try {
			while(!possoTerminare()) {
				inCorso.await();
			}
			System.out.println("Visita terminata");
			tourFinito.signalAll();
		} finally {
			l.unlock();
		}
	}


	@Override
	public void turistaInizia() throws InterruptedException {
		l.lock();
		Thread turista =Thread.currentThread();
		try {
			while(gruppoFormato()) {
				gruppoFormato.await();
			}
			postiLiberi--;
			gruppo.add(turista);
			System.out.format("Turista %d, si unisce al gruppo %n", turista.getId());
			if(postiLiberi==0) {
				gruppoNonFormato.signal();
				System.out.println();
			}
		} finally {
			l.unlock();
		}
	}

	@Override
	public void turistaFine() throws InterruptedException {
		l.lock();
		try {
			while(!possoAndare()) {
				tourFinito.await();
			}
			System.out.format("Turista %d, lascia il gruppo %n", Thread.currentThread().getId());
			postiLiberi++;
			gruppo.remove();
			if(postiLiberi==TUR_PER_GRUPPO) {
				visitaConclusa=false;
				attesaConclusa=false;
				gruppoFormato.signalAll();
				System.out.println();
			}
		} finally {
			l.unlock();
		}
	}
	
	private boolean possoAndare() {
		return visitaConclusa;
	}

	private boolean possoTerminare() {
		return visitaConclusa;
	}
	
	private void attesaGruppo() throws InterruptedException{
		spiega=true;
		gruppoFormato.signalAll();
		TimeUnit.SECONDS.sleep(ATTESA_GRUPPO);
		spiega=false;
	}
	
	private boolean gruppoFormato() {
		return postiLiberi==0 || spiega;
	}
	
	private boolean possoIniziare() {
		return attesaConclusa;
	}
	
	private int stabilisciTempi(int max, int min){
		return r.nextInt(max-min+1)+min;
	}
	
	private void attesaParte(int x) throws InterruptedException{
		TimeUnit.SECONDS.sleep(x);
	}
	
	public static void main(String[] args) {
		TourVar tl=new TourVar(20);
		tl.test(80);
	}
	
	
}
