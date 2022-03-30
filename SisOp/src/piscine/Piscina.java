package piscine;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Piscina {
	
	protected int corsie;
	protected ArrayList<Thread> [] postiCorsia;
	protected int NUO_PER_CORSIA;
	
	public Piscina(int corsie, int n) {
		this.corsie=corsie;
		NUO_PER_CORSIA=n;
		postiCorsia=new ArrayList[corsie];
		for(int i=0; i<corsie; i++)
			postiCorsia[i]=new ArrayList<>(NUO_PER_CORSIA);
		
	}
	
	protected abstract int scegliCorsia(int nCorsia) throws InterruptedException;
	protected abstract void esci(Thread t) throws InterruptedException;
	protected abstract void apri() throws InterruptedException;
	protected abstract void chiudi() throws InterruptedException;
	
	
	protected void cacciaFuori() throws InterruptedException{
		for(int i=0; i<postiCorsia.length; i++)
			for(int j=0; j<postiCorsia[0].size(); j++)
			esci(postiCorsia[i].remove(j));
	}
	
}
