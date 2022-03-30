package poo.polinomi;
import java.util.*;
public class PolinomioLL extends PolinomioAstratto{
	
	private LinkedList<Monomio> lista= new LinkedList<>();
	
	/*
	@Override
	public int size() {
		return lista.size(); //più efficace del size di default
	}*/
	
	@Override
	public PolinomioLL crea() {
		return new PolinomioLL(); //qui si usa il costruttore di defaul di Java
	}
	
	@Override
	public Iterator<Monomio> iterator(){
		return lista.iterator();
	}
	
	public void add(Monomio m) {
		if(m.getCoeff()==0)
			return;
		ListIterator<Monomio> lit=lista.listIterator();
		boolean flag= false; // se ho effettuato l'add 
		while(lit.hasNext() && !flag) {
			Monomio mcorr=lit.next();
			if(mcorr.equals(m)) {
				Monomio s=mcorr.add(m);
				if(s.getCoeff()==0)
					lit.remove();
				else 
					lit.set(s);
				flag=true;
			}//if
			else if(mcorr.compareTo(m)>0) {
				lit.previous();
				lit.add(m);
				flag=true;
			}
		}//while
		if(!flag) {
			lit.add(m);
		}
	}//add
	
	
	public static void main (String [] args) {
		Polinomio p=new PolinomioLL();
		p.add(new Monomio (-3,0));
		p.add(new Monomio (4,2));
		p.add(new Monomio (-7,5));
		p.add(new Monomio (8,2));
		p.add(new Monomio (10,3));
		p.add(new Monomio (0,8));
		System.out.println(p.size());
		Monomio m= new Monomio(2,2);
		p=p.mul(m);
		System.out.println(p);
	}
	
	
	
	
}
