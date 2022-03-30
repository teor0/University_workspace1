package aziendaAgricola;
import java.util.concurrent.locks.*;
import java.util.*;
public class AziendaLock extends Azienda{

	private Lock l = new ReentrantLock();
	private Condition paga = l.newCondition();
	private Condition preleva = l.newCondition();
	private Condition carica = l.newCondition();
	private LinkedList<Thread> cassa = new LinkedList<Thread>();
	private LinkedList<Thread> magazzino = new LinkedList<Thread>();
	
	public AziendaLock(int sacchetti){
		super(sacchetti);
	}
	public void paga(int numSacchi){
		l.lock();
		try{
			cassa.add(Thread.currentThread());
			while(!possoPagare()){
				paga.await();
			}
			cassa.remove();
			incasso += numSacchi * 3;
			paga.signalAll();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			l.unlock();
		}
	}//paga
	public boolean possoPagare(){
		return Thread.currentThread() == cassa.getFirst();
	}
	
	public void ritira(){
		l.lock();
		try{
			magazzino.add(Thread.currentThread());
			while(!possoPrelevare()){
				preleva.await();
			}
			magazzino.remove();
			nSacchi--;
			System.out.println("Sacchetti: "+nSacchi);
			if(nSacchi == 0)
				carica.signal();
			
			preleva.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			l.unlock();
		}
	}//ritira
	
	public boolean possoPrelevare(){
		return Thread.currentThread() == magazzino.getFirst() && nSacchi > 0;
	}
	
	public void caricaSacchi(){
		l.lock();
		try{
			while(nSacchi > 0){
				carica.await();
			}
			nSacchi+= sacchettiIniziali;
			System.out.println("Sacchetti: "+nSacchi);
			preleva.signalAll();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			l.unlock();
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		AziendaLock azienda = new AziendaLock(200);
		int numClienti  = 100;
		azienda.test(numClienti);
	}
	
	
	
}
