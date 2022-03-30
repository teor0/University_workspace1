package poo.figure;
public class Quadrato extends Figura {

	private double l;
	
	public Quadrato(int lato) {
		super(lato);
	}
	
	public double getLato() {
		return l;
	}
	
	@Override
	public double perimetro() {
		return l*4;
	}

	@Override
	public double area() {
		return l*l;
	}
	

}
