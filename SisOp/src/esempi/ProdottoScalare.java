package esempi;
public class ProdottoScalare extends Thread {
	
	private int a[];
	private int b[];
	private int inizio, fine;
	private int prodottoScalare = 0;

	public ProdottoScalare(int[] a, int[] b, int i, int f) {
		this.a = a;
		this.b = b;
		this.inizio = i;
		this.fine = f;
	}

	public void run() {
		for (int i = inizio; i <= fine; i++) {
			prodottoScalare += a[i] * b[i];
		}
	}

	
	public int getProdottoScalare() {
		try {
			this.join();//senza join c'è il rischio di ottenere il prodotto parziale e non completo
			//in quanto il thread potrebbe non essere terminato
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return prodottoScalare;
	}
}
