package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<T> extends AbstractQueue<T> {
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
		//first=last=null; rende sia first che last null
		first=null;
		last=null;
		size=0;
	}
	
	public void add(T e) {
		Nodo<T> n=new Nodo<>();
		n.info=e;
		if(last==null)//coda vuota
			first=n;
		else
			last.next=n;//se la coda non è vuota 
		last=n;//in ogni caso l'ultimo è n
		size++;
	}
	
	public T remove() {
		if(size==0)
			throw new NoSuchElementException();
		T x=first.info;
		first=first.next;
		if(first==null)//se il primo diventa null significa che c'era solo un elemento solo
			last=null;
		size--;
		return x;
	}//remove
	
	public T peek() {
		if(first==null)
			throw new NoSuchElementException();
		return first.info;
	}//peek
	
	public Iterator<T> iterator(){
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<T>{
		
		private Nodo<T> pre=null;
		private Nodo<T> cur=null;
		
		public boolean hasNext() {
			if(cur==null)
				return first!=null;//se ancora non ho consumato il primo ho next se first!=null
			return cur.next!=null;//ho consumato il primo quindi devo vedere next!=null
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
			else if(cur==last){//ha sicurmente almeno 2 elementi
				last=pre;
				last.next=null;
			}
			else {
				pre.next=cur.next;
			}
			size--;
			cur=pre;
		}	
	}
	
	public static void main(String[] args) {
		LinkedQueue<Integer> q=new LinkedQueue<>();
		q.add(2);
		q.add(4);
		q.add(6);
		q.add(9);
		q.add(10);
		System.out.println(q.toString());
		q.remove();
		System.out.println(q.toString());
	}
	
	
}//LinkedQueue
