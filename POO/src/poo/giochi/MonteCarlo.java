package poo.giochi;
import poo.geometria.*;
public class MonteCarlo {
	private int n, contaEventi;
	
	public MonteCarlo(int n) {
		//PRE:n>>1
		if(n<=1)
			throw new IllegalArgumentException();
		this.n=n;
	}
	
	public double pi() {
		contaEventi=0;
		Punto origine= new Punto();
		for(int e=0; e<n; e++) {
			double x=Math.random()*2-1;
			double y=Math.random()*2-1;
			Punto p= new Punto (x,y);
			double d=origine.distanza(p);
			if(d<=1)
				contaEventi++;
		}
		double pi=4*(double) contaEventi/n;
		return pi;
		
	}
}
