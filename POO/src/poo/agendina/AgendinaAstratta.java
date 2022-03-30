package poo.agendina;

import java.util.Iterator;

public abstract class AgendinaAstratta implements Agendina{
	public String toString() {
		StringBuilder sb=new StringBuilder(300);
		for( Nominativo n: this )
			sb.append( n+"\n" );
		return sb.toString();
	}//toString
	public boolean equals( Object x ) {
		if( !(x instanceof Agendina) ) return false;
		if( x==this ) return true;
		Agendina a=(Agendina)x;
		if( this.size()!=a.size() ) return false;
		Iterator<Nominativo> i1=this.iterator(), i2=a.iterator();
		while( i1.hasNext() ) {
			Nominativo n1=i1.next();
			Nominativo n2=i2.next();
			if( !n1.equals(n2) ) return false;
		}
		return true;
	}//equals
	public int hashCode() {
		final int M=43;
		int h=0;
		for( Nominativo n: this )
			h=h*M+n.hashCode();
		return h;
	}//hashCode
}//AgendinaAstratta
