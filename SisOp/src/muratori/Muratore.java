package muratori;

import java.util.concurrent.TimeUnit;

public class Muratore extends Thread{

	private int[] MATERIALE= {500,700};
	private int LAVORO=1;
	private int RIPOSO=5;
	protected int tipo;
	private Casa c;
	
	public Muratore(int t, Casa c) {
		this.c=c;
		tipo=t;
	}
	
	public void run() {
		while(true) {
			try {
				prepara();
				if(c.inizia(tipo)) {
					//lavora();
					c.termina(tipo);
					riposa();
				}
				else
					break;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getTipo() {
		return tipo;
	}
	
	private void prepara() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(MATERIALE[tipo]);
	}
	

		
	
	private void lavora() throws InterruptedException {
		TimeUnit.SECONDS.sleep(LAVORO);
	}
	
	private void riposa() throws InterruptedException {
		TimeUnit.SECONDS.sleep(RIPOSO);
	}
	
	
}
