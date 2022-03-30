package poo.util;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class StackConcatenato<T> extends StackAstratto<T> {
	private static class Nodo<E>{
		E info;
		Nodo<E> next;
	}
	
	private Nodo<T> first=null;
	private int size=0;
	
	@Override
	public int size() {
		return size;
	}
	
	//push mette un nuovo elemento SEMPRE in testa
	public void push(T e) {
		Nodo<T> n=new Nodo<>();
		n.info=e;
		n.next=first;
		first=n;
		size++;
	}
	
	public T pop(){
		if(first==null)
			throw new NoSuchElementException();
		T e=first.info;
		first=first.next;
		size--;
		return e;
	}
	
	public T peek() {
		if(first==null)
			throw new NoSuchElementException();
		return first.info;
	}
	
	public Iterator<T> iterator(){
		return new StackIterator();
	}
	
	private class StackIterator implements Iterator<T>{
		private Nodo<T> pre=null;
		private Nodo<T> cur=null;
		
		public boolean hasNext() {
			if(cur==null)//iteratore ancora non partito
				return first!=null;
			return cur.next!=null;//il prossimo nodo deve essere diverso da null
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
			return cur.info;//ritorno la fino dopo essere andato avanti
		}
		
		public void remove() {
			if(pre==cur)
				throw new IllegalStateException();
			if(cur==first)
				first=first.next;
			else
				pre.next=cur.next; //bypass
			size--;
			cur=pre;//deve puntare ad un elemento già consumato
		}
		
	}
	
	
}
