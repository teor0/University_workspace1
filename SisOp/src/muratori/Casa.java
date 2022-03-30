package muratori;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Casa {

	protected int pareti;
	protected int paretiFatte=0;
	protected final int filePerMuro;
	protected int fileAttuali;
	protected static final int MATTONE=0, CEMENTO=1;
	
	
	public Casa(int p, int file) {
		pareti=p;
		filePerMuro=file;
		fileAttuali=0;
	}
	
	protected abstract boolean inizia (int t) throws InterruptedException;
	protected abstract void termina(int t) throws InterruptedException;
	
	
	public void test(int mattonieri, int cementieri) {
		Muratore[] ma=new Muratore[mattonieri];
		Muratore[] ce=new Muratore[cementieri];
		System.out.println(pareti);
		for(int i=0; i<ma.length; i++)
			ma[i]=new Muratore(0,this);
		for(int c=0; c<ce.length; c++)
			ce[c]=new Muratore(1, this);
		for(int i=0; i<ma.length; i++)
			ma[i].start();
		for(int c=0; c<ce.length; c++)
			ce[c].start();
	}
	
}
