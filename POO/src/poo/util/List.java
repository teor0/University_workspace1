package poo.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

public interface List<T> extends Iterable<T>{
	default int size() {
		int c=0;
		for( T x: this ) 
			c++;
		return c;
	}//size
	default void clear() {
		Iterator<T> it=this.iterator();
		while( it.hasNext() ) {
			it.next();
			it.remove();
		}
	}
	default void addFirst( T e ) {
		ListIterator<T> lit=this.listIterator();
		lit.add(e);
	}//addFirst
	
	default void addLast( T e ) {
		ListIterator<T> lit=this.listIterator( size() );
		lit.add(e);		
	}//addLast
	
	default T getFirst() {
		return this.listIterator().next();
	}//getFirst
	
	default T getLast() {
		return this.listIterator(size()).previous();
	}//getLast
	
	default T removeFirst() {
		ListIterator<T> lit=this.listIterator();
		T x=lit.next();
		lit.remove();
		return x;
	}//removeFirst
	default T removeLast() {
		ListIterator<T> lit=this.listIterator(size());
		T x=lit.previous();
		lit.remove();
		return x;
	}//removeLast
	
	default boolean isEmpty() {
		return !listIterator().hasNext();
	}//isEmpty
	
	default boolean isFull() {
		return false;
	}//isFull
	
	static <T> void sort( List<T> lista, Comparator<? super T> c ){//sarebbe un bubble sort
		if( lista.size()<=1 ) return;
		boolean scambi=true;
		int limite=lista.size();
		int pus=0; //pos ultimo scambio
		while( scambi ) {
			ListIterator<T> lit=lista.listIterator();
			T x=lit.next();
			T y=null;
			int pos=1; 
			scambi=false; //ottimismo
			while( pos<limite ) {
				y=lit.next();
				if( c.compare(x,y)>0 ) {
					lit.set(x);
					lit.previous(); 
					lit.previous(); 
					lit.set(y);
					lit.next(); lit.next(); //la seconda next equivale a: x=lit.next();
					scambi=true; pus=pos;
				}
				else { x=y; }
				pos++;
			}//while interno
			limite=pus;
		}//while esterno		
	}//sort
	ListIterator<T> listIterator();
	ListIterator<T> listIterator( int pos );
}//List

