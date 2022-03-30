package poo.eratostene;

import java.util.Iterator;

public abstract class CrivelloAstratto implements Crivello{
	
	public String toString() {
		StringBuilder sb=new StringBuilder(500);
		int c=0;
		for( int x: this ) {
			sb.append( String.format("%10d", x) );
			c++;
			if( c%10==0 ) 
				sb.append('\n');
		}
		sb.append('\n');
		return sb.toString();
	}
	
	public int hashCode() {
		final int M=83;
		int h=0;
		for(int x: this)
			h=h+M*x;
		return h;
	}
	
	public boolean equals(Object x) {
		if(!(x instanceof Crivello))
			return false;
		if(this==x)
			return true;
		Crivello c= (Crivello) x;
		if(c.size()!=this.size())
			return false;
		Iterator<Integer> it1=this.iterator();
		Iterator<Integer> it2=c.iterator();
		while(it1.hasNext()) {
			if(it1.next()!=it2.next())
				return false;
		}
		return true;
	}
	
	//equals e hashCode da scrivere come esercizio
	
}//CrivelloAstratto
