package threadOrdinati;

import java.util.concurrent.TimeUnit;
public class Atleta extends Thread{
	
	private int nMaglia;
	private Gara g;
	private boolean flag=true;
	
	public Atleta(int nMaglia, Gara g) {
		this.g=g;
		this.nMaglia=nMaglia;
	}
	
	
	@Override
	public void run() {
		try {
			while(flag) {
				g.corri(nMaglia);
				riposa();
				g.fineCorsa(nMaglia);
				riposoLungo();
				flag=false;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}//run
	
	private void riposa() throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
	}
	
	private void riposoLungo() throws InterruptedException {
		TimeUnit.SECONDS.sleep(10);
	}
	
	
	
	public String toString() {
		return ""+nMaglia;
	}

}
