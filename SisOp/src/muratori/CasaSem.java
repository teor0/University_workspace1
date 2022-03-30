package muratori;

import java.util.concurrent.Semaphore;

public class CasaSem extends Casa{

	private Semaphore mutex=new Semaphore(1,true);
	private Semaphore[] azioneDaEseguire=new Semaphore[2];
	private Semaphore permesso;
	
	public CasaSem(int p, int file) {
		super(p,file);
		permesso=new Semaphore(0);
		azioneDaEseguire[MATTONE]=new Semaphore(0);
		azioneDaEseguire[CEMENTO]=new Semaphore(1);
	}	
	
	@Override
	protected boolean inizia(int t) throws InterruptedException{
		azioneDaEseguire[t].acquire();
		mutex.acquire();
		if(fileAttuali<filePerMuro && paretiFatte!=pareti) {
			permesso.release();
			mutex.release();
			return true;
		}
		mutex.release();
		return false;
	}

	@Override
	protected void termina(int t) throws InterruptedException{
		permesso.acquire();
		mutex.acquire();
		lavora(t);
		if(fileAttuali==filePerMuro) {
			paretiFatte++;
			System.out.println("PARETE COMPLETATA!");
			fileAttuali=0;
			if(paretiFatte==pareti)
				System.out.println("CASA COMPLETATA");
		}
		mutex.release();
		azioneDaEseguire[1-t].release();
	}
	
	private void lavora(int t) {
		System.out.format("Muratore %d mette una fila di %s %n", Thread.currentThread().getId(), 
				t==MATTONE? "mattoni":"cemento");
		if(t==MATTONE)
			System.out.println(fileAttuali+=1);
	}
	
	public static void main(String[] args) {
		CasaSem c=new CasaSem(2,5);
		/*c.test(5,7);*/
	}

}
