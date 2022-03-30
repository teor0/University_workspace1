package poo.concorrenza;
public class Processo extends Thread {
	public enum Tipo {A,B}
	private Risorsa m;
	private Tipo id;
	
	public Processo( Tipo id, Risorsa m ) {
		this.id=id;
		this.m=m;
	}
	
	private void pausa() {
		try {
			Thread.sleep( (int)(Math.random()*(5000-1000)+1000) );
		}catch( InterruptedException e ) {}
	}
	
	public void run() {
		while( true ) {
			pausa(); //il processo fa altre cose
			m.richiesta(id);
			System.out.println("Processo "+id+" usa la risorsa.");
			pausa(); //il processo usa la risorsa
			m.rilascio(id);
			System.out.println("Processo "+id+" rilascia la risorsa.");
		}
	}
}
