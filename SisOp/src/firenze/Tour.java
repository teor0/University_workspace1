package firenze;

import java.util.LinkedList;

public abstract class Tour {
	
	protected int MIN_PARTE=4, MAX_PARTE=5;
	protected int MIN_PAU=1, MAX_PAU=2;
	protected int TUR_PER_GRUPPO;
	protected int postiLiberi;
	protected int ATTESA_GRUPPO=5;
	protected LinkedList<Thread> gruppo=new LinkedList<>();
	
	public Tour(int turisti) {
		TUR_PER_GRUPPO=turisti;
		postiLiberi=turisti;
	}
	
	public int getPostiLiberi() {
		return postiLiberi;
	}
	
	
	abstract void attendiFormazioneGruppo() throws InterruptedException;
	abstract void visitaInizia() throws InterruptedException;
	abstract void visitaFine() throws InterruptedException;
	abstract void turistaInizia() throws InterruptedException;
	abstract void turistaFine() throws InterruptedException;
	
	public void test(int turisti) {
		Guida g=new Guida(this);
		g.start();
		Turista[] t=new Turista[turisti];
		for(int i=0; i<t.length; i++) {
			t[i]=new Turista(this);
			t[i].start();
		}
	}
	
}
