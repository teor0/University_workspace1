package strutture;
import java.util.*;
public class Albero<T extends Comparable<? super T>> implements Iterable<T> {

	private static class Nodo<E>{
		E info;
		Nodo<E> fS, fD;
	}
	
	private Nodo<T> radice=null;
	
	public int size() {
		return size(radice);
	}
	
	private int size(Nodo<T> radice) {
		if(radice==null)
			return 0;
		return 1+size(radice.fS)+size(radice.fD);
	}
	
	public void clear() {
		radice=null;
	}
	
	public boolean isEmpty() {
		return size()==0;
	}
	
	public boolean isFull() {
		return false;
	}
	
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
	
	public int altezza() {
		return altezza(radice,-1);
	}
	
	private int altezza(Nodo<T> radice, int h) {
		if(radice==null)
			return h;
		h++;
		int aS=altezza(radice.fS, h);
		int aD=altezza(radice.fD, h);
		if(aS>aD)
			h=aS;
		else
			h=aD;
		return h;
	}
	
	public T getRadice() {
		return radice.info;
	}
	
	public T getFS(Nodo<T> rad) {
		return rad.fS.info;
	}
	
	public T getFD(Nodo<T> rad) {
		return rad.fD.info;
	}
	
	public void add(T x) {
		radice=add(radice,x);
	}
	
	private Nodo<T> add(Nodo<T> radice, T e) {
		if(radice==null) {
			Nodo<T> n=new Nodo<>();
			n.info=e;
			return n;
		}
		if(radice.info.compareTo(e)>0) {
			radice.fS=add(radice.fS,e);
			return radice;
		}
		radice.fD=add(radice.fD,e);
		return radice;
	}
	
	public void remove(T x) {
		radice=remove(radice,x);
	}
	
	private Nodo<T> remove(Nodo<T> radice, T e){
		if(radice==null)
			return null;
		if(radice.info.compareTo(e)>0) {
			radice.fS=remove(radice.fS,e);
			return radice;
		}
		if(radice.info.compareTo(e)<0) {
			radice.fD=remove(radice.fD,e);
			return radice;
		}
		
		if(radice.fS==null && radice.fD==null)
			return null;
		if(radice.fS==null)
			return radice.fD;
		if(radice.fD==null)
			return radice.fS;
		//entrambi i figli esistono
		
		if(radice.fD.fS==null) {//non esiste fS
			radice.info=radice.fD.info;
			radice.fD=radice.fD.fD;
			return radice;
		}
		
		Nodo<T> padre=radice.fD;
		Nodo<T> figlio=radice.fS;
		while(figlio.fS!=null) {
			padre=figlio;
			figlio=figlio.fS;
		}
		radice.info=figlio.info;
		padre.fS=figlio.fD;
		return radice;
	}
	
	public void inOrder(List<T> l) {
		inOrder(radice,l);
	}
	
	private void inOrder(Nodo<T> radice, List<T> l) {
		if(radice!=null) {
			inOrder(radice.fS, l);
			l.add(radice.info);
			inOrder(radice.fD, l);
		}
	}
	
	public void preOrder(List<T> l) {
		preOrder(radice,l);
	}
	
	private void preOrder(Nodo<T> radice, List<T> l) {
		if(radice!=null) {
			l.add(radice.info);
			preOrder(radice.fS,l);
			preOrder(radice.fD,l);
		}
	}
	
	public void postOrder(List<T> l) {
		postOrder(radice,l);
	}
	
	private void postOrder(Nodo<T> radice, List<T> l) {
		if(radice!=null) {	
			postOrder(radice.fS,l);
			postOrder(radice.fD,l);
			l.add(radice.info);
		}
	}
	
	public void visitaPerLivelli(LinkedList<T> visitati) {
		if(radice==null)
			return;
		LinkedList<Nodo<T>> coda=new LinkedList<>();
		coda.addLast(radice);
		while(!coda.isEmpty()) {
			Nodo<T> r=coda.removeLast();
			visitati.addLast(r.info);
			if(r.fS!=null)
				coda.addLast(r.fS);
			if(r.fD!=null)
				coda.addLast(r.fD);
		}
	}
	
	public boolean bilanciato() {
		return bilanciato(radice);
	}
	
	private boolean bilanciato(Nodo<T> radice) {
		if(radice==null)
			return true;
		int sS=size(radice.fS);
		int sD=size(radice.fD);
		if(Math.abs(sS-sD)>1)
			return false;
		return bilanciato(radice.fS) && bilanciato(radice.fD);
	}
	
	public T get(T x){
		return get(radice,x);
	}
	
	private T get(Nodo<T> radice, T x){
		if(radice==null)
			return null;
		if(radice.info.equals(x))
			return radice.info;
		if(radice.info.compareTo(x)>0)
			return get(radice.fS,x);
		return get(radice.fD,x);
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Albero))
			return false;
		if(o==this)
			return true;
		Albero<T> a=(Albero<T>) o;
		return equals(this.radice, a.radice);
	}
	
	private boolean equals(Nodo<T> r1, Nodo<T> r2) {
		if(r1==null && r2==null)
			return true;
		if(r1==null || r2==null)
			return false;
		if(!r1.info.equals(r2.info))
			return false;
		return equals(r1.fS, r2.fS) && equals(r1.fD, r2.fD);	
	}
	
	public int hashCode() {
		return hashCode(radice);
	}
	
	private int hashCode(Nodo<T> radice) {
		if(radice==null)
			return 0;
		int h=0;
		h=radice.hashCode();
		int hS=radice.fS.hashCode();
		int hD=radice.fD.hashCode();
		h+=hS+hD;
		return h;
	}

	/*public Iterator<T> iterator(){
		return new IteratoreLinkato();
	}*/
	
	public Iterator<T> iterator(){
		return new IteratoreStack();
	}
	
	private class IteratoreLinkato implements Iterator<T>{
		
		private LinkedList<T> l=new LinkedList<>();
		private Iterator<T> it=null;
		private T cur;
		
		public IteratoreLinkato() {
			preOrder(l);
			it=l.iterator();
		}

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public T next() {
			cur=it.next();
			return cur;
		}
		
		public void remove() {
			it.remove();
			Albero.this.remove(cur);
		}
	}
	
	private class IteratoreStack implements Iterator<T>{

		private StackConcatenato<Nodo<T>> s=new StackConcatenato<>();
		private Nodo<T> cur;
		
		private void caricaStack(Nodo<T> radice) {
			if(radice==null)
				return;
			s.push(radice);
			while(radice.fS!=null) {
				s.push(radice.fS);
				radice=radice.fS;
			}
		}
		
		public IteratoreStack() {
			if(radice!=null)
				caricaStack(radice);
		}
		
		@Override
		public boolean hasNext() {
			return !s.isEmpty();
		}

		@Override
		public T next() {
			if(!hasNext())
				throw new NoSuchElementException();
			cur=s.pop();
			if(cur.fD!=null)
				caricaStack(cur.fD);
			return cur.info;
		}

		public void remove() {
			if(cur==null)
				throw new IllegalStateException();
			 Albero.this.remove(cur.info);
		}	
	}

}
