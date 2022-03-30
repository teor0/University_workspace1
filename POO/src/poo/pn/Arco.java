package poo.pn;

public class Arco {

	private Posto p;
	private int peso;
	
	public Arco(Posto p) {
		this.p=p;
		peso=1;
	}
	
	public Arco(Posto p, int peso) {
		this.p=p;
		if(peso<=0)
			throw new IllegalArgumentException();
		this.peso=peso;
	}
	
	public Posto getPosto() {
		return p;
	}
	
	public int getPeso() {
		return peso;
	}
}
