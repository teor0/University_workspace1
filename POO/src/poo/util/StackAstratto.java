package poo.util;
import java.util.*;
public abstract class StackAstratto<T> implements Stack<T>{
	public String toString(){
		StringBuffer sb=new StringBuffer(100);
		sb.append('[');
		Iterator<T> it=iterator();
		while( it.hasNext() ){
			sb.append( it.next() );
			if( it.hasNext() ) 
				sb.append(',');
		}
		sb.append(']');
		return sb.toString();
	}//toString
	
	public boolean equals(Object o) {
		if(!(o instanceof Stack))
			return false;
		if(o==this)
			return true;
		Stack<T> s=(Stack<T>) o;
		if(this.size()!=s.size())
			return false;
		Iterator<T> it1=this.iterator();
		Iterator<T> it2=s.iterator();
		while(it1.hasNext()) {
			T e1=it1.next();
			T e2=it2.next();
			if(!e1.equals(e2))
				return false;
		}
		return true;
	}
	
	public int hashCode() {
		final int M=83;
		int h=0;
		for(T e:this) {
			h=h*M+e.hashCode();
		}
		return h;
	}
	
}
