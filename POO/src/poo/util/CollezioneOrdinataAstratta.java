package poo.util;

import java.util.Iterator;

public abstract class CollezioneOrdinataAstratta<T extends Comparable<? super T>> implements CollezioneOrdinata<T> {
	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if(!(o instanceof CollezioneOrdinata))
			return false;
		if(o==this)
			return true;
		CollezioneOrdinata<T> c= (CollezioneOrdinata<T>) o;
		if(c.size()!=this.size())
			return false;
		Iterator<T> it1=this.iterator();
		Iterator<T> it2=c.iterator();
		while(it1.hasNext()) {
			T e1=it1.next();
			T e2=it2.next();
			if(!e2.equals(e1))
				return false;
		}//while
		return true;
	}
	public String toString() {
		StringBuilder sb=new StringBuilder(200);
		sb.append("[");
		Iterator<T> it=iterator();
		while( it.hasNext() ) {
			T x=it.next();
			sb.append(x);
			if( it.hasNext() ) 
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}//toString
	
	public Iterator<T> iterator(){
		return this.iterator();
	}
	
	public int hashCode(){
		int p=43;
		int h=0;
		for( T e: this )
			h=h*p+e.hashCode();
		return h;	
	}//hashCode	
	
}//CollezioneOrdinataAstratta
