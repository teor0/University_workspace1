package esempi;

public class StampanteT extends Thread{
	
	private int from;
	private int to;
	
	public StampanteT(int from, int to){
		this.from=from;
		this.to=to;
	}
	
	public void run() {
		for(int i=from; i<=to; i++)
			System.out.println(i+ " ");
		System.out.println();
	}

}
