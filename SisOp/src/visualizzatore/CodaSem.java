package visualizzatore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CodaSem extends Coda{

	private Semaphore mutex;
	private Semaphore inserisci;
	private Semaphore preleva;
	
	
	
	public CodaSem(int capienza) {
		super(capienza);
		inserisci=new Semaphore(capienza);
		preleva=new Semaphore(0);
		mutex=new Semaphore(1);
	}

	@Override
	public void inserisci(String[] stringhe){
		try {
			inserisci.acquire(stringhe.length); //acquisisco permessi per inserire
			//in altre parole aspetto di avere x posti liberi
			mutex.acquire();
			for(int i=0; i<stringhe.length; i++)
				lista.add(stringhe[i]);
			preleva.release(stringhe.length);
			mutex.release();
			}catch(InterruptedException e) {
			}
	}

	@Override
	public String visualizza(){
		String ret=null;
		try {
			preleva.acquire(); //tento di prelevare ovvero vedo se la coda e' vuota
			mutex.acquire();
			ret=lista.removeFirst();
			inserisci.release(); //creo un posto libero
			mutex.release();
		}catch(InterruptedException e) {
		}
		return ret;
	}
	
	public static void main(String[] args) {
		CodaSem c=new CodaSem(100);
		c.test(10);
	}
	
	

}
