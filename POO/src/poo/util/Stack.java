package poo.util;
import java.util.*;
/* Classe di gestione di una collezione di oggeti come uno stack
 * implementazione di uno stack, come quello visto in reti logiche
 * basato su FIFO si comporta esattamente come uno stack vero e proprio
 */
public interface Stack<T> extends Iterable<T>{
	default int	size() {
		int c=0;
		for(T e:this)
			c++;
		return c;
	}
	default void clear() {
		Iterator<T> it= iterator();
		while(it.hasNext()) {
			it.next();
			it.remove();
		}
	}
	void push(T x); //permette di inserire in cima allo stack un elemento
	
	default T pop() {
		//ritorna l'elemento in cima allo stack e lo rimuove
		Iterator<T> it=iterator();
		T x=it.next();
		it.remove();
		return x;
	}
	
	default T peek() {
		//ritorna l'elemento in cima allo stack 
		Iterator<T> it=iterator();
		T x=it.next();
		return x;
	}
	
	default boolean isEmpty() {
		return size()==0;
	}
	
	default boolean isFull(){
		return false;
	}
	

}
