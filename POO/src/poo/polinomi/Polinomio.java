package poo.polinomi;
import java.util.Iterator;
public interface Polinomio extends Iterable<Monomio> {
	
	default int size() {
		int c=0;
		for(Iterator<Monomio> it=iterator(); it.hasNext(); it.next(),c++);
			return c;
	}
	
	void add(Monomio m);
	
	Polinomio crea(); //metodo factory, una sorta di costruttore da implementare nelle classe concrete
	
	default Polinomio add(Polinomio p) {
		Polinomio somma= crea();
		for(Monomio m: this)
			somma.add(m);
		for(Monomio m: p)
			somma.add(m);
		return somma;
	}
	
	default Polinomio mul(Polinomio p) {
		Polinomio prodotto=crea();
		for(Monomio m:this)
			prodotto=prodotto.add(p.mul(m));
		return prodotto;
	}
	
	default Polinomio mul(Monomio m) {
		Polinomio prodotto=crea();
		for( Monomio m1: this)
			prodotto.add(m.moltiplicazione(m1)); //moltiplico ogni monomio che compone this, per il monomio m e poi ne faccio la somma
		return prodotto;
	}
	
	default Polinomio derivata() {
		Polinomio d=crea();
		for( Monomio m: this )
			if( m.getGrado()>0 )
				d.add( new Monomio( m.getCoeff()*m.getGrado(), m.getGrado()-1 ) );
		return d;
	}//derivata
	
	default double valore(double x) {
		double v=0.0D;
		for(Monomio m:this)
			v=v+m.getCoeff()*Math.pow(x, m.getGrado());
		return v;
	}
}
