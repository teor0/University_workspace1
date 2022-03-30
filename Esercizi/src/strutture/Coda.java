package strutture;

import java.util.Iterator;
import java.util.NoSuchElementException;
public class Coda<T> implements Iterable<T>{
	
	private static class Nodo<E>{
		E info;
		Nodo<E> next;
	}
	private Nodo<T> first=null, last=null;
	private int size;
	
	public int size() {
		return size;
	}
	
	public void clear() {
		size=0;
		first=null;
		last=null;
	}
	
	public boolean contains(T x){
		Nodo<T> cur=first;
		while(cur!=null) {
			if(cur.info.equals(x))
				return true;
			cur=cur.next;
		}
		return false;
	}
	
	public T peek() {
		if(first==null)
			throw new NoSuchElementException();
		return first.info;
	}
	
	public void add(T e) {
		Nodo<T> n=new Nodo<>();
		n.info=e;
		if(first==null)
			first=n;
		else
			last.next=n;
		last=n;
		size++;
	}
	
	public T remove() {
		if(size==0)
			throw new NoSuchElementException();
		T x=first.info;
		first=first.next;
		if(first==null)
			last=null;
		size--;
		return x;
	}
	
	
	public Iterator<T> iterator(){
		return new CodaIterator();
	}
	
	public String toString() {
		Iterator<T> it=iterator();
		StringBuilder sb=new StringBuilder(30);
		sb.append("[");
		while(it.hasNext()) {
			sb.append(it.next());
			if(it.hasNext())
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
	
	
	private class CodaIterator implements Iterator<T>{
		
		private Nodo<T> cur=null;
		private Nodo<T> pre=null;
		
		public boolean hasNext() {
			if(cur==null)
				return first!=null;
			return cur.next!=null;
		}
		
		public T next() {
			if(!hasNext())
				throw new NoSuchElementException();
			if(cur==null)
				cur=first;
			else {
				pre=cur;
				cur=cur.next;
			}
			return cur.info;
		}
		
		public void remove() {
			if(pre==cur)
				throw new IllegalStateException();
			if(cur==first) {
				first=first.next;
				if(first==null)
					last=null;
			}
			else if(cur==last){
				last=pre;
				pre.next=null;
			}
			else {
				pre.next=cur.next;
			}
			size--;
			cur=pre;
		}
	}//iterator
	
	public static void main(String[] args) {
		Coda<Integer> c=new Coda<>();
		System.out.println(c.toString());
		c.add(2);
		c.add(5);
		c.add(6);
		System.out.println(c.contains(5));
		System.out.println(c.toString());
	}

}
