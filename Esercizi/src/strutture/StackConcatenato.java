package strutture;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackConcatenato<T> implements Iterable<T>{

	private static class Nodo<E>{
		E info;
		Nodo<E> next;
	}
	
	private Nodo<T> first=null;
	private int size=0;
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	
	public void push(T el) {
		Nodo<T> n=new Nodo<>();
		n.info=el;
		n.next=first;
		first=n;
		size++;
	}
	
	public T peek() {
		if(first==null)
			throw new NoSuchElementException();
		return first.info;
	}
	
	public T pop() {
		if(first==null)
			throw new NoSuchElementException();
		T e=first.info;
		first=first.next;
		size--;
		return e;
	}
	
	public Iterator<T> iterator(){
		return new StackIterator();
	}
	
	private class StackIterator implements Iterator<T>{
		
		private Nodo<T> pre=null;
		private Nodo<T> cur=null;
		
		public boolean hasNext() {
			if(cur==null)
				return first!=null;
			return cur.next!=null;
		}
		
		public T next(){
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
			if(cur==first) 
				first=first.next;
			else 
				pre.next=cur.next;
			size--;
			cur=pre;
		}	
	}
	
	
}
