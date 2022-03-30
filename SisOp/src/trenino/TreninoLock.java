package trenino;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

public class TreninoLock extends Trenino{

	private Lock l=new ReentrantLock();
	private Condition[] aTerra=new Condition[NUM_CABINE];
	private Condition turistiScesi=l.newCondition();
	private Condition faiSalire=l.newCondition();
	private Condition faiScendere=l.newCondition();
	private Condition turistiSaliti=l.newCondition();
	private Condition possoEntrare=l.newCondition();
	private boolean[] pieno=new boolean[NUM_CABINE];
	private boolean[] giro=new boolean[NUM_CABINE];
	private boolean uscitaConsentita=false;
	private boolean ingressoConsentito=false;
	
	public TreninoLock() {
		super();
		for(int i=0; i<aTerra.length; i++)
			aTerra[i]=l.newCondition();
		for(int j=0; j<pieno.length; j++) {
			pieno[j]=false;
			giro[j]=false;
		}
	}
	
	
	@Override
	public void turSali() throws InterruptedException {
		l.lock();
		try {
			while(!possoSalire()) {
				possoEntrare.await();
			}
			cabina[cabinaSuolo]++;
			System.out.println(toString());
			if(cabina[cabinaSuolo]==NUM_TUR_CABINA) {
				pieno[cabinaSuolo]=true;
				ingressoConsentito=false;
				turistiSaliti.signal();
			}
		} finally {
			l.unlock();
		}	
	}

	@Override
	public void turScendi() throws InterruptedException {
		l.lock();
		try {
			while(!possoScendere()) {
				aTerra[cabinaSuolo].await();
			}
			cabina[cabinaSuolo]--;
			System.out.println(toString());
			if(cabina[cabinaSuolo]==0) {
				giro[cabinaSuolo]=false;
				pieno[cabinaSuolo]=false;
				uscitaConsentita=false;
				faiSalire.signal();
			}
		} finally {
			l.unlock();
		}
		
	}


	@Override
	public void impFaiSalire() throws InterruptedException {
		l.lock();
		try {
			while(salitaNegata()) {
				faiSalire.await();
			}
			ingressoConsentito=true;
			System.out.println("faccio SALIRE nella cabina "+ cabinaSuolo);
			possoEntrare.signalAll();
		} finally {
			l.unlock();
		}
		
	}

	@Override
	public void impMuovi() throws InterruptedException {
		l.lock();
		try {
			while(!cabinaPiena()) {
				turistiSaliti.await();
			}
			ingressoConsentito=false;
			uscitaConsentita=false;
			TimeUnit.MILLISECONDS.sleep(1200);
			System.out.println("muovo la ruota");
			TimeUnit.MILLISECONDS.sleep(1200);
			cabinaSuolo++;
			scatti++;
			if(cabinaSuolo==10)
				cabinaSuolo=0;
			System.out.println("s" +scatti);
			if(scatti>=10 && scatti%10==cabinaSuolo) {
				giro[cabinaSuolo]=true;
				faiScendere.signal();
			}
			faiSalire.signalAll();
		} finally {
			l.unlock();
		}
	}
	
	@Override
	public void impFaiScendere() throws InterruptedException {
		l.lock();
		try {
			while(uscitaNegata()) {
				faiScendere.await();
			}
			System.out.println("faccio SCENDERE dalla cabina "+ cabinaSuolo);
			uscitaConsentita=true;
			aTerra[cabinaSuolo].signalAll();
		} finally {
			l.unlock();
		}
		
	}
	
	private boolean uscitaNegata() {
		return cabina[cabinaSuolo]>0 && (!giro[cabinaSuolo]);
	}
	
	private boolean possoSalire() {
		return ingressoConsentito && !cabinaPiena();
	}
	
	private boolean possoScendere() {
		return  uscitaConsentita && cabina[cabinaSuolo]>0;
	}
	
	private boolean cabinaPiena() {
		return pieno[cabinaSuolo];
	}
	
	private boolean salitaNegata() {
		return pieno[cabinaSuolo] && uscitaConsentita;
	}
	
	public static void main(String[] args) {
		TreninoLock tl=new TreninoLock();
		tl.test(140);
	}

}
