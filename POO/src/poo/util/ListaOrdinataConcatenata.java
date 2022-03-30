package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaOrdinataConcatenata<T extends Comparable<? super T>> extends CollezioneOrdinataAstratta<T> {
	private static class Nodo<E>{
		E info;
		Nodo<E> next;
	}
	
	private Nodo<T> testa=null;
	private int size=0;
	
	public int size() {
		return size;
	}
	
	public void clear() {
		testa=null;
		size=0;
	}
	
	public boolean contains(T x) {
		Nodo<T> cor=testa;
		while(cor!=null) {
			if(cor.info.equals(x))
				return true;
			if(cor.info.compareTo(x)>0)
				return false;
			cor=cor.next;
		}
		return false;
	}
	
	public void add(T x) {
		Nodo<T> nuovo=new Nodo<>();
		nuovo.info=x;
		if(testa==null || testa.info.compareTo(x)>=0) {
			nuovo.next=testa;
			testa=nuovo;
		}
		else {
			Nodo<T> pre=testa, cor=testa.next;
			while(cor!=null && cor.info.compareTo(x)<0) {
				pre=cor;
				cor=cor.next;
			}
			pre.next=nuovo;
			nuovo.next=cor;
		}
		size++;
	}
	
	public void remove(T x) {
		Nodo<T> pre=null, cor=testa;
		while(cor!=null && cor.info.compareTo(x)<0) {
			pre=cor;
			cor=cor.next;
		}
		if(cor!=null && cor.info.equals(x)) {
			if(cor==testa) {
				testa=testa.next;
			}
			else {
				pre.next=cor.next;
			}
			size--;
		}
	}
	
	public T get(T x) {
		Nodo<T> cor=testa;
		while(cor!=null) {
			if(cor.info.equals(x))
				return cor.info;
			if(cor.info.compareTo(x)>0)
				return null;
			cor=cor.next;
		}
		return null;
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	
	public boolean isFull() {
		return false;
	}
	
	public Iterator<T> iterator() {
		return new MyIterator();
	}
	
	private class MyIterator implements Iterator<T>{
		
		Nodo<T> pre=null;
		Nodo<T> cor=null;
		
		public boolean hasNext() {
			if(cor==null)
				return testa!=null; //un prossimo elemento esiste se c'è la testa
			return cor.next!=null;
		}
		
		public T next() {
			if(!hasNext())
				throw new NoSuchElementException();
			if(cor==null)
				cor=testa;
			else {
				pre=cor;
				cor=cor.next;
			}
			return cor.info;
		}
		
		public void remove() {
			if(pre==cor)
				throw new IllegalStateException();
			if(cor==testa) {
				testa=testa.next;
			}
			else {
				pre.next=cor.next;
			}
			size--;
			cor=pre;//cor sta puntando ad un nodo da eliminare perciò devo riportare cor al nodo corrente
			//ovvero quello prima dell'eliminazione
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}//ListaOrdinataConcatenata
