package poo.insiemi;

import java.util.Iterator;

public interface Insieme<T> extends Iterable<T> {
	
	/*
	  Definire un'interfaccia lnsieme<T> generica nel tipo T degli elementi ed iterabile, corrispondente al concetto
di insieme matematico di oggetti (non sono ammesse le repliche, né è importante l'ordine). Insieme deve
esporre (almeno) i seguenti metodi:
Progettare quindi una classe astratta lnsiemeAstratto<T> che implementa lnsieme<T> e concretizza quanti
più metodi è possibile.
	 */
	default int size(){
		int c=0;
		for(T e:this)
			c++;
		return c;
	}
	
	default boolean eVuoto() {
		return size()==0;
	}
	
	default boolean appartiene(T e) {
		for(T x:this)
			if(e.equals(x))
				return true;
		return false;
	}
	
	boolean aggiungi(T e);
	//aggiunge e all'insieme, ritorna true se la struttura è cambiata a seguito dell'aggiunta
	
	default boolean rimuovi(T e) {
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			T x=it.next();
			if(e.equals(x)) {
				it.remove();
				return true;
			}
		}
		return false;	
	}
	
	Insieme<T> unione(Insieme <T> altro);
	//costruisce e ritorna l’insieme unione tra this e altro
	Insieme<T> intersezione(Insieme<T> altro);
	//costruisce e ritorna l'insieme intersezione
	Insieme<T> differenza(Insieme<T> altro);
	//costruisce e ritorna l’insieme differenza “this-altro’’, costituito cioè dagli elementi appartenenti a this ma non altro
	Insieme<T> differenzaSimmetrica(Insieme<T> altro);
	//ossia “this-altro” unito a “altro-this”.
	
	
	
	
	
	
	
	
	
	
}
