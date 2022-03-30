package poo.util;
import java.util.*;
public class StackArray<T> extends StackAstratto<T> {
	
	private T[] pila;
	private int size=0; // il primo elemento libero
	
	@SuppressWarnings("unchecked")
	public StackArray(int n){
		if(n<1)
			throw new IllegalArgumentException();
		pila=(T[]) new Object[n];
	}
	
	public StackArray() {
		this(10);
	}
	
	public int size() {
		return size;
	}
	
	public void push(T e) {
		if(size==pila.length)//si deve aumentare la dimensione
			pila=java.util.Arrays.copyOf(pila, size*2);
		pila[size]=e;//elemento in cima
		size++; //aumenta la CIMA
	}
	
	public T pop() {
		if(size==0)
			throw new NoSuchElementException();
		T x=pila[size-1];//ultimo elemento
		pila[size-1]=null;
		size--;
		return x;
	}
	
	public T peek() {
		if(size==0)
			throw new NoSuchElementException();
		return pila[size-1];
	}
	
	public Iterator<T> iterator(){
		return new StackArrayIterator();
	}
	
	private class StackArrayIterator implements Iterator<T>{
		private int cor=size;//si parte dalla cima
		private boolean rimuovibile=false;
		
		public boolean hasNext() {
			if(cor==size)
				return size>0;
			return cor>0;//fino a 0 ci sono elementi 
		}//hasNext
		
		public T next() {
			if(!hasNext())
				throw new NoSuchElementException();
			cor--;
			rimuovibile=true;
			return pila[cor];
		}//next
		
		public void remove() {
			if(!rimuovibile)
				throw new IllegalStateException();
			rimuovibile=false;
			for(int i=cor+1; i<size; i++)//spostiamo gli elementi a sinistra
				pila[i-1]=pila[i];
			pila[size-1]=null;//non ci deve essere nessun elemento a size-1
			size--;
		}
		
	}
	
	
	public static void main(String [] args) {
		StackArray<Integer> s=new StackArray<>(6);
		LinkedList<Integer> l=new LinkedList<>();
		l.addFirst(1);
		l.addFirst(2);
		l.addFirst(3);
		System.out.println(l);
	}
	
}

