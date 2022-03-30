package poo.geometria;

public class Disco extends Punto implements FiguraPiana {
	private double raggio;
	
	public Disco(double raggio) {
		super(0,0);
		this.raggio=raggio;
	}
	
	public Disco(double x, double y, double r) {
		super(x,y);
		raggio=r;
	}
	
	public Disco(Punto p, double r) {
		super(p);
		raggio=r;
	}
	
	public Disco(Disco d) {
		super(d.getX(), d.getY());
		this.raggio=d.raggio;
	}
	
	public double perimetro() {
		return (Math.PI*2)+raggio;
	}
	
	public double area() {
		return raggio*raggio*Math.PI;
	}
	
	public double getRaggio() {
		return raggio;
	}
	
	public String toString() {
		return "Disco di raggio=" + raggio + " e centro in " + super.toString();
	}
	
	

}
