package casello;

public abstract class Casello {

	protected int porte;
	protected int tariffa;
	protected  int incasso=0;
	
	public Casello(int porte, int tariffa) {
		this.porte=porte;
		this.tariffa=tariffa;
	}
	
	public int getIncasso() {
		return incasso;
	}
	
	public int getPorte() {
		return porte;
	}
	
	public abstract void paga(int nPorta, int km)throws InterruptedException;
	public abstract void scegliPorta(int nPorta)throws InterruptedException;
	
	public void test(int V) {
		Veicolo[] veicoli = new Veicolo[V];
		for (int i = 0; i < V; i++) {
			veicoli[i] = new Veicolo(this);
		}
		
		for (int i = 0; i < V; i++) {
			veicoli[i].start();
		}
		
		for (int i = 0; i < V; i++) {
			try {
				veicoli[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
		System.out.format("Incasso finale è di %d%n", getIncasso());		
	}
	
}
