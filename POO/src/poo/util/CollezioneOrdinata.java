package poo.util;

import java.util.Iterator;

public interface CollezioneOrdinata<T extends Comparable<? super T>> extends Iterable<T>{
	
	default int size() {
		int ret=0;
		for(T x:this)
			ret++;
		return ret;
	}
	
	default void clear() {
		Iterator<T> it= iterator();
		while(it.hasNext()) {
			it.next();
			it.remove();
		}
	}
	default boolean contains( T x ){
		for(T e:this) {
			if(e.equals(x))
				return true;
			if(e.compareTo(x)>0)
				return false;
		}
		return false;
	}
	
	void add( T x );
	
	default void remove( T x ) {
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			T e=it.next();
			if(e.equals(x)) {
				it.remove();
				break;
			}
		}
	}
	
	default T get( T x ) {
		for(T e:this) {
			if(e.equals(x))
				return e;
			if(e.compareTo(x)>0)
				return null;
		}
		return null;
	}
	
	default boolean isEmpty() {
		return size()==0;
	}
	
	default boolean isFull() {
		return false;
	}
	
}//CollezioneOrdinata
