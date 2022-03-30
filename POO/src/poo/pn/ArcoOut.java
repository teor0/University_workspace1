package poo.pn;

public class ArcoOut extends Arco{
	
	private Posto p;
	private int peso;
	
	public ArcoOut(Posto p) {
		super(p);
		this.p=p;
		peso=1;
	}
	
	public ArcoOut(Posto p,int peso) {
		super(p,peso);
		this.p=p;
		if(peso<=0)
			throw new IllegalArgumentException();
		this.peso=peso;
	}

}
