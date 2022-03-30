package poo.util;

public abstract class AbstractVector<T> implements Vector<T>{
	public String toString() {
		StringBuilder sb=new StringBuilder(300);
		sb.append("[");
		for( int i=0; i<this.size(); ++i ) {
			sb.append( this.get(i) );
			if( i<size()-1 ) sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}//toString
	public boolean equals( Object o ) {
		if( !(o instanceof Vector) ) return false;
		if( o==this ) return true;
		Vector<T> v=(Vector<T>)o;
		if( this.size()!=v.size() ) return false;
		for( int i=0; i<v.size(); ++i )
			if( !this.get(i).equals(v.get(i)) ) return false;
		return true;
	}//equals
	public int hashCode() {
		final int M=83; //numero primo per eseguire lo shuffling
		int h=0;
		for( int i=0; i<this.size(); ++i )
			h=h*M+this.get(i).hashCode();
		return h;
	}//hashCode
}//AbstractVector
