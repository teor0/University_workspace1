package poo.polinomi;
import java.util.*;
public abstract class PolinomioAstratto implements Polinomio{
	
	public String toString() {
		StringBuilder sb= new StringBuilder(300);
		Iterator<Monomio> i= this.iterator();
		boolean flag=true;
		while(i.hasNext()) {
			Monomio m=i.next();
			if(m.getCoeff()>0 && !flag)
				sb.append('+');
			sb.append(m);
			if(flag)
				flag= !flag;
		}
		return sb.toString();
	}
	
	public boolean equals(Object x) {
		if(!(x instanceof Polinomio))
			return false;
		if(x==this)
			return true;
		Polinomio p= (Polinomio) x;
		if(p.size()!=this.size())
			return false;
		Iterator<Monomio> i1=this.iterator();
		Iterator<Monomio> i2=p.iterator();
		while(i1.hasNext()) {
			Monomio m1=i1.next();
			Monomio m2=i2.next();
			if(m1.getCoeff()!=m2.getCoeff() || m1.getGrado()!=m2.getGrado())
				return false;
		}
		return true;
	}
	
	public int hashCode() {
		final int M=43;
		int h=0;
		for(Monomio m: this)
			h=h*M+(String.valueOf(m.getCoeff())+String.valueOf(m.getGrado())).hashCode();
		return h;
	}

}
