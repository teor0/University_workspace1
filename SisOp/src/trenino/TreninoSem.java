package trenino;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TreninoSem extends Trenino{

	private Semaphore mutex=new Semaphore(1);
	private Semaphore[] postiCabina=new Semaphore[NUM_CABINE];
	private Semaphore[] cabinaVuota=new Semaphore[NUM_CABINE];
	private Semaphore puoMuovere=new Semaphore(0);
	private Semaphore faiScendere=new Semaphore(0);
	private Semaphore faiSalire=new Semaphore(1);
	private Semaphore puoSalire=new Semaphore(0);
	
	public TreninoSem() {
		super();
		for(int i=0; i<postiCabina.length; i++) {
			postiCabina[i]=new Semaphore(0);
			cabinaVuota[i]=new Semaphore(1);
		}
	}

	@Override
	public void turSali() throws InterruptedException {
		puoSalire.acquire();
		mutex.acquire();
		cabina[cabinaSuolo]++;
		if(cabina[cabinaSuolo]==NUM_TUR_CABINA)
			puoMuovere.release();
		System.out.println(toString());
		mutex.release();
	}

	@Override
	public void turScendi() throws InterruptedException {
		postiCabina[cabinaSuolo].acquire();
		mutex.acquire();
		cabina[cabinaSuolo]--;
		System.out.println(toString());
		if(cabina[cabinaSuolo]==0)
			cabinaVuota[cabinaSuolo].release();
		mutex.release();
		
	}

	@Override
	public void impFaiScendere() throws InterruptedException {
		faiScendere.acquire();
		mutex.acquire();
		System.out.println("faccio scendere");
		postiCabina[cabinaSuolo].release(NUM_TUR_CABINA);
		mutex.release();
	}

	@Override
	public void impFaiSalire() throws InterruptedException {
		faiSalire.acquire();
		cabinaVuota[cabinaSuolo].acquire();
		mutex.acquire();
		System.out.println("faccio salire");
		puoSalire.release(NUM_TUR_CABINA);
		mutex.release();
	}

	@Override
	public void impMuovi() throws InterruptedException {
		puoMuovere.acquire();
		mutex.acquire();
		System.out.println("muovo la ruota");
		TimeUnit.SECONDS.sleep(2);
		cabinaSuolo++;
		if(cabinaSuolo==10)
			cabinaSuolo=0;
		scatti++;
		if(scatti>=10 && scatti%10==cabinaSuolo)
			faiScendere.release();
		faiSalire.release();
		mutex.release();
	}
	
	public static void main(String[] args) {
		TreninoSem t=new TreninoSem();
		t.test(100);
	}
}
