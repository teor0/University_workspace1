package poo.figure;
import poo.geometria.*;
public class Cono extends Cerchio implements FiguraSolida{

	private double r;
	private double h=10D;
	private double a=15D;
	
	public Cono(double r) {
		super(r);
	}
	
	
	@Override
	public double areaLaterale() {
		double ret=2*Math.PI*r*h;
		return ret;
	}

	@Override
	public double volume() {
		return Math.PI*(r*r)*h;
	}

	@Override
	public double perimetro() {
		return Math.PI*(r*r) + Math.PI*a*r;
	}

	@Override
	public double area() {
		return Math.PI*(r*r);
	}

}
