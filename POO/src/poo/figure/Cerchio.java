package poo.figure;

public class Cerchio extends Figura{
	
	public Cerchio(double raggio) {
		super(raggio);
	}
	
	public Cerchio(Cerchio c) {
		super(c.getDimensione());
	}
	
	public double getRaggio() {
		return getDimensione();
	}
	
	public double perimetro() {
		return 2*Math.PI*getDimensione();
	}
	
	public double area() {
		double r=getDimensione(); //è più efficiente di fare getRaggio
		return r*r*Math.PI;
	}
	
	public String toString() {
		return "Cerchio di raggio= " + getDimensione();
	}

}
