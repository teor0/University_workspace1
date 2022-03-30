package poo.figure;
import poo.geometria.*;
public abstract class Figura implements FiguraPiana {
	private double dimensione;
	
	public Figura(double dimensione) {
		this.dimensione=dimensione;
	}
	
	protected double getDimensione() {
		return dimensione;
	}
	
	public abstract double perimetro();
	
	public abstract double area(); //potrei pure evitare di scrivere i due metodi perimetro ed area perchè li 
	//implementiamo da FiguraPiana
	
	

}
