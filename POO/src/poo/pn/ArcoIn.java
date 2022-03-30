package poo.pn;

public class ArcoIn extends Arco{

	private Posto p;
	private int peso;
	
	public ArcoIn(Posto p) {
		super(p);
		this.p=p;
		peso=1;
	}
	
	public ArcoIn(Posto p, int peso) {
		super(p,peso);
		this.p=p;
		if(peso<=0)
			throw new IllegalArgumentException();
		this.peso=peso;
	}

}
