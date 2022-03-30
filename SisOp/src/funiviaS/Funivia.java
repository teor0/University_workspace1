package funiviaS;

import java.util.LinkedList;

public abstract class Funivia {

	protected int postiLiberi;
	protected int pIniziali;
	protected LinkedList<Turista> seggiole;
	
	public Funivia(int posti) {
		pIniziali=posti;
		postiLiberi=posti;
		seggiole=new LinkedList<>();
	}
	
	
	public abstract void pilotaStart() throws InterruptedException;
	public abstract void pilotaEnd() throws InterruptedException;
	public abstract void turistaSali(int i) throws InterruptedException;
	public abstract void turistaScendi(int i) throws InterruptedException;
	
	public void test(int nT) {
		Pilota p=new Pilota(this);
		Turista[] turisti=new Turista[nT];
		for(int i=0; i<nT; i++)
			turisti[i]=new Turista(this);
		p.start();
		for(int i=0; i<nT; i++)
			turisti[i].start();
		
	}
	
	
	
	
}
