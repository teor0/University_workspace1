package esempi;

public class Sommatore extends Thread{
		
	private int da, a, somma;
	
	public Sommatore(int da, int a) {
		this.da=da;
		this.a=a;
	}
	
	public int getSomma() throws InterruptedException{
		this.join();
		return somma;
	}
	
	public void run() {
		somma=0;
		for(int i=da; i<=a; i++)
			somma+=i;
	}
	
	
	
}
