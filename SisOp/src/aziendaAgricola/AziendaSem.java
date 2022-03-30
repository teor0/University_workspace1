package aziendaAgricola;

import java.util.concurrent.Semaphore;

public class AziendaSem extends Azienda {

	private Semaphore cassa=new Semaphore(1,true);
	private Semaphore mutex;
	private Semaphore carica;
	private Semaphore sacchi;
	
	public AziendaSem(int nSacchi) {
		super(nSacchi);
		mutex=new Semaphore(1);
		carica=new Semaphore(0);
		sacchi=new Semaphore(nSacchi,true);
	}
	
	
	@Override
	public void ritira() {
		try {
			sacchi.acquire();
			mutex.acquire();
			nSacchi--;
			if(nSacchi==0)
				carica.release();
			System.out.println("Sacchetti: "+nSacchi);
			mutex.release();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void caricaSacchi() {
		try {
			carica.acquire();
			mutex.acquire();
			nSacchi+=sacchettiIniziali;
			System.out.println("Sacchetti: "+nSacchi);
			mutex.release();
			sacchi.release(nSacchi);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void paga(int nSacchi) {
		try {
			cassa.acquire();
			incasso+=(nSacchi*3);
			cassa.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		AziendaSem a=new AziendaSem(200);
		a.test(100);

	}
}
