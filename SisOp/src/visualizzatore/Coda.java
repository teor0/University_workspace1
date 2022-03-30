package visualizzatore;

import java.util.LinkedList;

public abstract class Coda {

	protected LinkedList<String> lista;
	protected int capienza;
	
	public Coda(int capienza){
		this.capienza=capienza;
		lista=new LinkedList<>();
	}
	
	public abstract void inserisci(String[] stringhe);
	public abstract String visualizza();	
	
	public void test(int n) {
		Visualizzatore visualizzatore = new Visualizzatore(this);

		int numUtenti = n;
		Utente utenti[] = new Utente[numUtenti];
		for (int i = 0; i < numUtenti; i++) {
			utenti[i] = new Utente(i, this);
		}

		visualizzatore.start();

		for (int i = 0; i < numUtenti; i++) {
			utenti[i].start();
		}
	}
	
}
