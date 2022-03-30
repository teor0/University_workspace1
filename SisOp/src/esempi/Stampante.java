package esempi;

public class Stampante {
	
	private int from;
	private int to;
	
	public Stampante(int from, int to){
		this.from=from;
		this.to=to;
	}

	public void print(){
		for(int i=from; i<=to; i++)
			System.out.println(i+ " ");
		System.out.println();
	}
	
}
