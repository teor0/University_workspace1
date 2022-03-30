package poo.util;

import java.util.Iterator;
public interface Queue<T> extends Iterable<T> {
	//implementare quanti piu metodi e possibile mediante l'iterator
	//ipotesi: iteratore restituisce gli elementi dal piu' vecchio al piu' giovane
	default int size() {
		int c=0;
		for(T e:this)
			c++;
		return c;
	}
	default void clear() {
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			it.next();
			it.remove();
		}
		
	}
	default void offer( T x ) { //add x in coda
		add(x);
	}
	
	void add( T x );
	
	default T remove() {
		//rimuove e ritorna l'elemento piu vecchio
		if(isEmpty())
			throw new RuntimeException("Coda vuota!");
		Iterator<T> it=iterator();
		T e=it.next();
		it.remove();
		return e;
	}
	
	default T poll(){ 
		if(isEmpty())
			throw new RuntimeException("Coda vuota!");
		return remove(); 
	} //rimuove e ritorna l'elemento piu vecchio
	
	T peek(); //ritorna l'elemento piu vecchio
	
	default boolean isEmpty() { 
		return !iterator().hasNext(); 
	}
	
	default boolean isFull() { 
		return false; 
	}
}//Queue
