package poo.util;

import java.util.Iterator;

public abstract class AbstractQueue<T> implements Queue<T>{
	//scrivere anche equals() e hashCode()
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("[");
		Iterator<T> it=iterator();
		while( it.hasNext() ) {
			sb.append( it.next() );
			if( it.hasNext() ) sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}//toString
	
	public int hashCode() {
		int M=43;
		int h=0;
		for(T e:this)
			h=h*M+e.hashCode();
		return h;
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Queue))
			return false;
		if(o==this)
			return true;
		Queue<T> c= (Queue<T>) o;
		if(c.size()!=this.size())
			return false;
		Iterator<T> it1=this.iterator();
		Iterator<T> it2=c.iterator();
		while(it1.hasNext()) {
			T e1=it1.next();
			T e2=it2.next();
			if(!e1.equals(e2))
				return false;
		}
		return true;
	}
}//AbstractQueue
