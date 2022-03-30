package poo.insiemi;

import java.util.Iterator;

public abstract class InsiemeAstratto<T> implements Insieme<T> {
	
	public String toString() {
		StringBuilder sb=new StringBuilder(50);
		sb.append("{");
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			sb.append(it.next());
			if(it.hasNext())
				sb.append(", ");
		}
		sb.append("}");
		return sb.toString();
	}//toString
	
	public int hashCode() {
		final int M=83;
		int h=0;
		for(T x:this)
			h=h*M+x.hashCode();
		return h;
	}//hashCode
	
	@SuppressWarnings("unchecked") //si utilizza per "zittire" il warning sul casting di o
	public boolean equals(Object o) {
		if(!(o instanceof Insieme))
			return false;
		if(o==this)
			return true;
		Insieme<T> i=(Insieme<T>) o;
		if(i.size()-this.size()!=0)
			return false;
		Iterator<T> it1=i.iterator();
		Iterator<T> it2=this.iterator();
		while(it1.hasNext()) {
			T x1=it1.next();
			T x2=it2.next();
			if(!x1.equals(x2))
				return false;
		}
		return true;
	}
	
}
