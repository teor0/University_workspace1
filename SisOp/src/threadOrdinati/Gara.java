package threadOrdinati;

import java.util.concurrent.locks.*;

public class Gara {
	
	private Lock l=new ReentrantLock();
	private Condition corre=l.newCondition();
	private static final int N=30;
	private boolean atletaCorre;
	private boolean[] haCorso=new boolean[N];
	private Atleta[] a;
	
	public Gara() {
		a=new Atleta[N];
		for(int i=0; i<N; i++)
			a[i]=new Atleta(i,this);
	}
	
	public void corri(int nMaglia) throws InterruptedException {
		l.lock();
		try{
			while(!puoCorrere(nMaglia)) {
				corre.await();
			}
			atletaCorre=true;
			System.out.println("atleta numero: "+ a[nMaglia]+ " corre");
			haCorso[nMaglia]=true;
		} finally {
			l.unlock();
		}
	}
	
	public void fineCorsa(int nMaglia) throws InterruptedException{
		l.lock();
		try {
			atletaCorre=false;
			System.out.println("atleta numero: "+ a[nMaglia]+ " smette di correre");
			corre.signalAll();
		} finally {
			l.unlock();
		}
	}
	
	private boolean puoCorrere(int nMaglia) {
		if(nMaglia==0)
			return true;
		return !atletaCorre && haCorso[nMaglia-1];
	}
	
	public void test() {
		for(int i=0; i<N; i++) {
			a[i].start();
		}
	}
	
	public static void main(String[] args) {
		Gara g=new Gara();
		g.test();
	}
	
}
