package poo.eratostene;
import java.util.Iterator;
import poo.util.*;
public class CrivelloLOC<T extends Comparable<? super T>> extends ListaOrdinataConcatenata<T> implements CrivelloOrdinato<T>{
	private ListaOrdinataConcatenata<Integer> lista= new ListaOrdinataConcatenata<>();
	private final int N;
	
	public CrivelloLOC(final int N) {
		if(N<=1)
			throw new IllegalArgumentException();
		for(int i=2; i<=N; i++)
			lista.add(i);
		this.N=N;
	}
	
	public String toString() {
		return lista.toString();
	}
	
	
	public boolean equals(Object o) {
		if(!(o instanceof CrivelloOrdinato))
			return false;
		if(o==this)
			return true;
		CrivelloOrdinato<T> c= (CrivelloOrdinato<T>) o;
		if(c.size()!=this.size())
			return false;
		Iterator<T> it1=this.iterator();
		Iterator<T> it2=c.iterator();
		while(it1.hasNext()) {
			T e1=it1.next();
			T e2=it2.next();
			if(!e1.equals(e2))
				return false;
		}//while
		return true;		
	}
	public void filtra() {
		for(int i=2; i<=Math.round(Math.sqrt(N)); i=(i==2)? i+1:i+2)
			if(lista.contains(i)) {
				int m=i+i;
				while(m<=N) {
					lista.remove(m);
					m=m+i;
				}
			}//if
	}//filtra;
	
	public static void main (String[] args) {
		CrivelloOrdinato<Integer> c= new CrivelloLOC<Integer>(100);
		CrivelloOrdinato<Integer> f= new CrivelloLOC<Integer>(50);
		f.filtra();
		c.filtra();
		System.out.println(f.hashCode());
		System.out.println(c);
	}
	
}
