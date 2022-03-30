package poo.geometria;

public class Punto{
	private double x,y;
	
	public Punto(){
		x=0;
		y=0;
	}
	public Punto(double x, double y){
		this.x=x;
		this.y=y;
	}
	public Punto(Punto p){
		x=p.x;
		y=p.y;
	}
	
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public void sposta(double x, double y){
		this.x=x;
		this.y=y;
	}
	public double distanza(Punto p){
		return Math.sqrt((p.x-this.x)*(p.x-this.x)+(p.y-this.y)*(p.y-this.y));
	}
	public String toString(){
		return "Punto("+ String.format("%1.2f", x) + "," + String.format("%1.2f", y)+ ")";
	}
}