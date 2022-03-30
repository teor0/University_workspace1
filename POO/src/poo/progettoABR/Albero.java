package poo.progettoABR;
import java.util.Iterator;
import poo.util.*;

public class Albero<T extends Comparable<? super T>> implements CollezioneOrdinata<T>{
	
	private static class Nodo<K>{
		K info;
		Nodo<K> fD, fS;
		int cfD, cfS;
	}
	
	private Nodo<T> radice=null;

	
	@Override
	public int size() {
		return size(radice);
	}
	
	private int size(Nodo<T> radice) {
		if(radice==null)
			return 0;
		return 1+size(radice.fS)+size(radice.fD);
	}
	
	@Override
	public void clear() {
		radice=null;
		radice.cfD=0;
		radice.cfS=0;
	}
	@Override
	public boolean contains(T e) {
		return contains(radice,e);
	}
	
	private boolean contains(Nodo<T> radice, T e) {
		if(radice==null)
			return false;
		if(radice.info.equals(e))
			return true;
		if(radice.info.compareTo(e)>0)
			return contains(radice.fS,e);
		return contains(radice.fD,e);
	}
	
	public void add(T e) {
		radice=add(radice,e);
	}
	
	private Nodo<T> add(Nodo<T> radice, T e) {
		if(radice==null) {
			Nodo<T> n=new Nodo<>();
			n.info=e;
			n.fD=null;
			n.fS=null;
			n.cfD=0;
			n.cfS=0;
			return n;
		}
		if(radice.info.compareTo(e)>0) {
			radice.fS=add(radice.fS,e);
			radice.cfS++;
			return radice;
		}
		radice.fD=add(radice.fD,e);
		radice.cfD++;
		return radice;
	}
	
	@Override
	public void remove(T e) {
		radice=remove(radice,e);
	}
	
	
	private Nodo<T> remove(Nodo<T> radice, T e){
		if(radice==null)
			return null;
		if(radice.info.compareTo(e)>0) {
			radice.fS=remove(radice.fS,e);
			radice.cfS--;
			return radice;
		}
		if(radice.info.compareTo(e)<0) {
			radice.fD=remove(radice.fD, e);
			radice.cfD--;
			return radice;
		}
		//l'elemento è proprio la radice
		if(radice.fD==null && radice.fS==null)
			return null;
		if(radice.fS==null)
			return radice.fD;
		if(radice.fD==null)
			return radice.fS;
		//ha entrambi i figli
		if( radice.fD.fS==null ) {//1 sotto caso: la radice destra e' il minimo
			//promozione
			radice.info=radice.fD.info;
			//rimozione "vittima"
			radice.fD=radice.fD.fD; //fatto il bypass
			radice.cfD--;
			return radice;
		}
		//2 sotto caso più generale
		Nodo<T> padre=radice.fD; //radice del sotto albero destro
		Nodo<T> figlio=padre.fS;
		while( figlio.fS!=null ) {
			padre=figlio; figlio=figlio.fS;
		}
		//figlio punta alla vittima
		//promozione
		radice.info=figlio.info;
		//eliminare il nodo figlio, che al più ha un solo figlio
		padre.fS=figlio.fD; //bypass
		return radice;	
	}	
	
	@Override
	public T get(T e) {
		return get(radice,e);
	}
	
	private T get( Nodo<T> radice, T e ) {
		if( radice==null ) 
			return null;
		if( radice.info.equals(e) ) 
			return radice.info;
		if( radice.info.compareTo(e)>0 ) 
			return get( radice.fS, e );
		return get( radice.fD, e );
	}//get
	
	public T getFD() {
		return radice.fD.info;
	}
	
	public int getCSAS() {
		return radice.cfS;
	}
	
	public int getCSAD() {
		return radice.cfD;
	}
	
	public T getFS() {
		return radice.fS.info;
	}
	
	@Override
	public boolean isEmpty() {
		return radice==null;
	}
	
	public void visual() {
		if(radice!=null)
			System.out.println("La radice è: " +radice.info);
		visual(radice);
	}
	
	private void visual(Nodo<T> radice){
		if(radice!=null) {
			Nodo<T> padre= radice;
			if(padre.fS!=null && padre.fD!=null) {
				System.out.println(radice.info+ " ha FS "+radice.fS.info +" e FD "+radice.fD.info );
			}
			else if(padre.fS!=null)
				System.out.println(radice.info+ " ha FS "+ radice.fS.info );
			else if(padre.fD!=null)
				System.out.println(radice.info+ " ha FD "+ radice.fD.info);
			visual(radice.fS);
			visual(radice.fD);
		}
		System.out.print("");
	}
	
	public void inOrder( List<T> lis ) {
		inOrder( radice, lis );
	}//inOrder
	private void inOrder( Nodo<T> radice, List<T> lis ) {
		if( radice!=null ) {
			inOrder( radice.fS, lis );
			lis.addLast( radice.info ); //visita la radice
			inOrder( radice.fD, lis );
		}
	}//inOrder
	
	public void preOrder( List<T> lis ) {//visita in ordine anticipato
		preOrder(radice,lis);
	}
	private void preOrder( Nodo<T> radice, List<T> lis ) {
		if( radice!=null ) {
			lis.addLast(radice.info); //prima la radice
			preOrder( radice.fS, lis );
			preOrder( radice.fD, lis );
		}
	}
	
	public void postOrder( List<T> lis ) {//visita in ordine posticipato
		postOrder( radice, lis );
	}
	private void postOrder( Nodo<T> radice, List<T> lis ) {
		if( radice!=null ) {
			postOrder( radice.fS, lis );
			postOrder( radice.fD, lis );
			lis.addLast( radice.info );
		}
	}
	
	public Iterator<T> iterator(){
		return new IteratoreAlbero();
	}
	
	private class IteratoreAlbero implements Iterator<T>{
		private StackArray<Nodo<T>> st= new StackArray<>();
		private Nodo<T> cor;
		
		public IteratoreAlbero() {
			if(radice==null)
				return;
			Nodo<T> rad=radice;
			st.push(rad);
			while(rad.fS!=null) {
				st.push(rad.fS);
				rad=rad.fS;
			}
		}//costruttore
		
		public boolean hasNext() {
			return !(st.size()==0);
		}
		
		public T next() {
			cor=st.pop();
			if(cor.fD!=null) {
				st.push(cor.fD);
				Nodo<T> alD=cor.fD;
				while(alD.fS!=null) {
					st.push(alD.fS);
					alD=alD.fS;
				}
			}
			return cor.info;
		}
		
		public void remove() {
			Albero.this.remove(cor.info);
		}
	}
	
	
	public boolean bilanciato() {
		int a=radice.cfS;
		int b=radice.cfD;
		int diff=Math.abs(a-b);
		if(diff==0 || diff==1)
			return true;
		return false;
	}
	
	public static void main(String [] args) {
		Albero<Integer> abr=new Albero<>();
		abr.add(12); abr.add(34); abr.add(-2); abr.add(-4); abr.add(5); abr.add(1); abr.add(7);
		abr.add(38); abr.add(-6); abr.add(-3); abr.add(31); abr.add(40); abr.add(29); abr.add(33);
		abr.visual();
		System.out.println();
		System.out.println(abr.bilanciato());
		System.out.println(abr.getCSAD());
		System.out.println(abr.getCSAS());
	}

	
}


