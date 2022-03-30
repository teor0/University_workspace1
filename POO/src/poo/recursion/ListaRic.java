package poo.recursion;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import poo.util.CollezioneOrdinata;

public class ListaRic<T extends Comparable<? super T>> implements CollezioneOrdinata<T> {
	//lista ordinata 
	private static class Nodo<E>{
		E info;
		Nodo<E> next;
		
		public String toString() {
			return info.toString();
		}
	}
	
	private Nodo<T> lista=null;
	private  int modCoun=0;
	
	public int size() {
		return size( lista );
	}
	
	private int size( Nodo<T> lista ) {
		if( lista==null ) 
			return 0;
		return 1+size( lista.next );
	}
	
	public boolean contains( T e ) {
		return contains( lista, e );
	}
	
	private boolean contains( Nodo<T> lista, T e ) {
		if( lista==null ) 
			return false;
		if( lista.info.equals(e) ) 
			return true;
		if( lista.info.compareTo(e)>0 ) 
			return false; //"ottimizzazione" ricerca lineare
		return contains( lista.next, e );
	}
	
	public boolean isEmpty() {
		return lista==null; 
	}
	public boolean isFull() { 
		return false; 
	}
	public void clear() { 
		lista=null; 
		modCoun++;
	}
	
	public T get( T e ) {
		return get( lista, e );
	}
	
	private T get( Nodo<T> lista, T e ) {
		if( lista==null ) 
			return null;
		if( lista.info.equals(e) )
			return lista.info;
		if( lista.info.compareTo(e)>0 ) 
			return null;
		return get( lista.next, e );
	}
	
	public void add( T e ) {
		lista=add( lista, e ); //certamente la lista cambia a seguito dell'inserimento di e
		modCoun++;
	}
	
	private Nodo<T> add( Nodo<T> lista, T e ){
		if( lista==null || lista.info.compareTo(e)>=0 ){
			Nodo<T> n=new Nodo<>();
			n.info=e; 
			n.next=lista;
			return n;
		}
		//la lista non e' vuota ed il suo primo elemento e' minore di e
		lista.next=add( lista.next, e );
		//la add funzionerà quando o avrò un elemento nullo oppure avrò un elemento minore di e
		return lista;
	}
	
	public ListaRic<T> copy(){
		
	}
	
	public void remove( T e ) {
		lista=remove( lista, e );
		modCoun++;
	}
	
	private Nodo<T> remove( Nodo<T> lista, T e ){
		if( lista==null ) 
			return null;
		if( lista.info.equals(e) ) {
			return lista.next; //eliminato il nodo in testa
		}
		if( lista.info.compareTo(e)>0 ) //se il nodo contiene un elemento più grande allora ho finito
			return lista;
		lista.next=remove( lista.next, e ); //lista residua potenzialmente cambia
		return lista;
	}
	
	public void reverse() {
		Nodo<T> nuovaTesta=lista;
		while(nuovaTesta.next!=null) {
			nuovaTesta=nuovaTesta.next;
		}//arrivo alla coda
		lista=reverse(nuovaTesta,nuovaTesta);
	}
	
	private Nodo<T> reverse(Nodo<T> testa, Nodo<T> capo) {
		if(testa==null)
			return capo;
		Nodo<T> pre=null;
		Nodo<T> cor=lista;
		while(cor.info!=testa.info) {
			pre=cor;
			cor=cor.next;
		}
		testa.next=pre;
		reverse(pre,capo);
		return capo;
	}
	
	public Iterator<T> iterator(){ 
		return new ListaRecIterator(); 
	}
	
	private class ListaRecIterator implements Iterator<T>{ 
		//sollevare anche la ConcurrentModificationException, quando serve
		
		private int conMod=modCoun;
		Nodo<T> pre=null, cor=null;
		
		public boolean hasNext() {
			if(cor==null)//se il corrente è null allora dobbiamo verificare se il primo elemento è nullo
				return lista!=null;
			return cor.next!=null;
		}
		public T next() {
			if(conMod!=modCoun)
				throw new ConcurrentModificationException();
			if( !hasNext() ) 
				throw new NoSuchElementException();
			if(cor==null)//se è null dobbiamo prendere la testa della lista
				cor=lista;
			else{
				pre=cor; //aggiorniamo i due puntatori a pre e cor
				cor=cor.next;
			}
			return cor.info;
		}
		
		public void remove() {
			if(conMod!=modCoun)
				throw new ConcurrentModificationException();
			if(pre==cor)
				throw new IllegalStateException();
			if(cor==lista)
				lista=lista.next;
			else 
				pre.next=cor.next;
			cor=pre;
			conMod++;
			modCoun++;
		}
	}//ListaRecIterator
	
	public String toString() {
		StringBuilder sb=new StringBuilder(100);
		sb.append("[");
		toString( lista, sb );
		sb.append("]");
		return sb.toString();
	}
	
	private void toString( Nodo<T> lista, StringBuilder sb ) {
		if( lista==null ) return;
		sb.append( lista.info );
		if( lista.next!=null ) sb.append(", ");
		toString( lista.next, sb );
	}
	
	@SuppressWarnings("unchecked")
	public boolean equals( Object x ) {
		if( !(x instanceof ListaRic) ) return false;
		if( x==this ) return true;
		ListaRic<T> l=(ListaRic<T>)x;
		return equals( lista, l.lista );
	}
	
	private boolean equals( Nodo<T> lista1, Nodo<T> lista2 ) {
		if( lista1==null && lista2==null ) 
			return true;
		if( lista1==null || lista2==null ) 
			return false;
		if( !lista1.info.equals(lista2.info) ) 
			return false;
		return equals( lista1.next, lista2.next );
	}
	
	private final int MUL=83;
	
	public int hashCode() {
		return hashCode( lista ); 
	}
	
	private int hashCode( Nodo<T> lista ) { 
		if(size()==0)
			return 0;
		return lista.hashCode()*MUL+ hashCode(lista.next);
	}
	
	public static void main( String[] args ) {
		ListaRic<String> ls=new ListaRic<>();
		ls.add("tana"); 
		ls.add("dado"); 
		ls.add("zinco"); 
		ls.add("casa"); 
		ls.add("abaco");
		System.out.println(ls);
		ls.reverse();
/*		System.out.println(ls+" size="+ls.size());
		ls.remove("dado");
		System.out.println(ls+" size="+ls.size());
		ls.add("lupo");
		System.out.println(ls+" size="+ls.size());
		System.out.println("Esiste lupo? "+ls.contains("lupo"));
		ListaRic<String> l2=new ListaRic<>();
		l2.add("lupo"); 
		l2.add("tana"); 
		l2.add("zinco");
		System.out.println("l2="+l2);
		System.out.println("Liste uguali? "+ls.equals(l2));*/
		System.out.println(ls);
		ls.reverse();
		System.out.println(ls);
		/*Iterator<String> lit=ls.iterator();
		while(lit.hasNext()) {
			String e=lit.next();
			//lit.remove();
			System.out.println(e);
		}
		System.out.println(ls);*/
	}
	
}//ListaRec
