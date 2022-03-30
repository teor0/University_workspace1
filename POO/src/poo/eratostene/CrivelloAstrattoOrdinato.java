package poo.eratostene;

import java.util.Iterator;

public abstract class CrivelloAstrattoOrdinato<T extends Comparable<? super T>> implements CrivelloOrdinato<T>{
	public String toString() {
		StringBuilder sb=new StringBuilder(500);
		Iterator<T> it=iterator();
		int c=0;
		while( it.hasNext() ) {
			T x=it.next();
			sb.append(String.format("%10", x));
			c++;
			if(c%10==0)
				sb.append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}
	
	public int hashCode() {
		final int M=83;
		int h=0;
		for(T x: this)
			h=h+M*x.hashCode();
		return h;
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
}
