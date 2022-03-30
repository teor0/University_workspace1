package poo.geometria;
import poo.util.*;
public class Triangolo implements FiguraPiana{
	private Punto p1; 
	private Punto p2; 
	private Punto p3;
	private double a,b,c;
	public Triangolo(Punto p1, Punto p2, Punto p3){
		if(!esisteTriangolo(p1,p2,p3))
			throw new IllegalArgumentException();
		a=p1.distanza(p2);
		b=p2.distanza(p3);
		c=p3.distanza(p1);
		this.p1=p1;
		this.p2=p2;
		this.p3=p3;	
	}
	public Triangolo(Triangolo t){
		this.p1=t.p1;
		this.p2=t.p2;
		this.p3=t.p3;
		this.a=t.a;
		this.b=t.b;
		this.c=t.c;
		
	}
	public Punto[] getVertici(){
		return new Punto[]{p1,p2,p3};
	}
	public double area(){
		double s=perimetro()/2;
		double a=p1.distanza(p2);
		double b=p2.distanza(p3);
		double c=p3.distanza(p1);
		return Math.sqrt(s*(s-a)*(s-b)*(s-c));
	}
	public double perimetro(){
		return a+b+c;
	}
	
	public double getA() {
		return a;
	}
	
	public double getB() {
		return b;
	}
	
	public double getC() {
		return c;
	}
	
	public boolean esisteTriangolo(Punto p1, Punto p2, Punto p3){
		double a=p1.distanza(p2);
		double b=p2.distanza(p3);
		double c=p3.distanza(p1);
		if(a>b+c||b>a+c||c>a+b)
			return false;
		return true;
	}
	public String toString(){
	return "Il triangolo ha vertici in: " + p1 + "," + p2 +" e "+ p3;
	}
	
	public String tipo() {
		if(Mat.sufficientementeProssimi(a, b) & Mat.sufficientementeProssimi(b,c))
			return "Triangolo equilatero";
		if(Mat.sufficientementeProssimi(a, b) || Mat.sufficientementeProssimi(a, c) || Mat.sufficientementeProssimi(b, c))
			return "Triangolo isoscele";
		return "Triangolo scaleno";
	}
}
