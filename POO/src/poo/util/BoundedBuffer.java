package poo.util;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BoundedBuffer<T> extends AbstractQueue<T> {
	//array gestito a coda che ha una capacità che non può essere violata
	//anche detto buffer circolare
	private T[] buffer;
	private int in; //in prima posizione libera
	private int out;// indice prima posizione occupata
	private int size;//elementi effettivi nella cosa
	private int modCounter;
	
	@SuppressWarnings("unchecked")//silenzia il casting 
	public BoundedBuffer( int n ) {
		if( n<1 ) throw new IllegalArgumentException();
		buffer=(T[])new Object[n];
		in=0; out=0; size=0;
		modCounter=0;
	}
	
	public int size() { 
		return size; 
	}
	@Override
	public boolean isFull() { 
		return size==buffer.length; 
	}
	
	public void add( T e ) {
		if( isFull() ) 
			throw new RuntimeException("Queue is Full!");
		buffer[in]=e;
		in=(in+1)%buffer.length;//incremento modulare 
		size++;
		modCounter++;
	}
	
	public void clear() {
		for( int i=out; i!=in; i=(i+1)%buffer.length )//stessa logica della add
			buffer[i]=null;
		in=0; 
		out=0; 
		size=0;
		modCounter++;
	}
	
	public T remove() {
		if( size==0 ) 
			throw new RuntimeException("Queue is Empty!!!");
		T x=buffer[out];
		out=(out+1)%buffer.length;
		size--;
		modCounter++;
		return x;
	}
	public T peek() {
		if( size==0 ) 
			throw new RuntimeException("Queue is Empty!!!");
		return buffer[out];		
	}
	public Iterator<T> iterator(){ return new BoundedBufferIterator(); }
	
	public class BoundedBufferIterator implements Iterator<T>{
		//si ignora la ConcurrentModificationException 
		//ovvero se vengono effettuate modifiche durante l'iterazione, si ha un errore
		// i=(i+1)%n  per incrementare i. se i=n-1 torno a 0. ecco la circolarità
		//i=(i-1+n)%n; per decrementare i. se i=0 torno a n-1.
		private int cor=-1;
		private boolean rimuovibile=false;
		private int modifiche;
		
		public BoundedBufferIterator() {
			//prendi contatore dall'oggetto outer e copialo qui
			modifiche=modCounter;
		}
		public boolean hasNext() {
			if( cor==-1 ) 
				return size>0;
			return (cor+1)%buffer.length!=in;
			//prendo l'indice del prossimo elemento
			//e questo indice deve essere diverso da quello della prima posizione libera
		}//hasNext
		public T next() {
			if( !hasNext() ) 
				throw new NoSuchElementException();
			if(modifiche!=modCounter)
				throw new ConcurrentModificationException();
			if( cor==-1 ) 
				cor=out;//solita consistenza, se non sono sul primo elemento allora ci vado
			else 
				cor=(cor+1)%buffer.length;
			rimuovibile=true;
			return buffer[cor];
		}//next
		public void remove() { 
			if( !rimuovibile ) 
				throw new IllegalStateException();
			if(modifiche!=modCounter)
				throw new ConcurrentModificationException();
			rimuovibile=false;
			int j=(cor+1)%buffer.length;//prima posizione utile da spostare
			while( j!=in ) { //left shift
				buffer[(j-1+buffer.length)%buffer.length]=buffer[j];
				j=(j+1)%buffer.length;
			}
			size--;
			cor=(cor-1+buffer.length)%buffer.length; //arretra cor
			in=(in-1+buffer.length)%buffer.length; //arretra in
			buffer[in]=null;
			modCounter++;
			modifiche++;
		}//remove
	}//BoundedBufferIterator
	
}//BoundedBuffer
