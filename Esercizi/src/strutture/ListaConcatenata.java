package strutture;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaConcatenata<T extends Comparable<?super T>> implements Iterable<T>{

	private static class Nodo<E>{
		E info;
		Nodo<E> next;
	}
	
	private Nodo<T> testa=null;
	private int size=0;
	
	public int size() {
		return size;
	}
	
	public void clear() {
		testa=null;
		size=0;
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	
	public boolean contains(T x) {
		Nodo<T> cur=testa;
		while(cur!=null){
			if(cur.info.equals(x))
				return true;
			if(cur.info.compareTo(x)>0)
				return false;
			cur=cur.next;
		}
		return false;
	}
	
	public void add(T x){
		Nodo<T> nuovo=new Nodo<>();
		nuovo.info=x;
		if(testa==null || testa.info.compareTo(x)>0) {
			nuovo.next=testa;
			testa=nuovo;
		}
		else {
			Nodo<T> cur=testa.next;
			Nodo<T> pre=testa;
			while(cur!=null && cur.info.compareTo(x)<0) {
				pre=cur;
				cur=cur.next;
			}
			pre.next=nuovo;
			nuovo.next=cur;
		}
		size++;
	}
	
	public void remove(T x) {
		Nodo<T> pre=null; 
		Nodo<T> cur=testa;
		while(cur!=null && cur.info.compareTo(x)<0) {
			pre=cur;
			cur=cur.next;
		}//cerco il nodo?
		if(cur!=null && cur.info.equals(x)) {
			if(cur==testa) {
				testa=testa.next;
			}
			else {
				pre.next=cur.next;
				cur=null;
			}
			size--;
		}
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder(20);
		sb.append("[");
		Iterator<T> it=this.iterator();
		while(it.hasNext()) {
			sb.append(it.next());
			if(it.hasNext())
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
	
	
	@Override
	public Iterator<T> iterator() {
		return new IteratoreMio();
	}
	
	private class IteratoreMio implements Iterator<T>{

		private Nodo<T> cur=null;
		private Nodo<T> pre=null;
		
		@Override
		public boolean hasNext() {
			if(cur==null)
				return testa!=null;
			return cur.next!=null;
		}

		@Override
		public T next() {
			if(!hasNext())
				throw new NoSuchElementException();
			if(cur==null)
				cur=testa;
			else {
				pre=cur;
				cur=cur.next;
			}
			return cur.info;
		}
		
		@Override
		public void remove(){
			if(pre==cur)
				throw new IllegalStateException();
			if(cur==testa)
				testa=testa.next;
			else {
				pre.next=cur.next;
			}
			size--;
			cur=pre;
		}
	}
	
	public static void main(String[] args) {
		ListaConcatenata<Integer> l=new ListaConcatenata<>();
		l.add(10);
		l.add(8);
		l.add(7);
		l.add(11);
		l.add(9);
		System.out.println(l.toString());
		System.out.println(l.size());
		l.remove(10);
		l.remove(7);
		/*Iterator<Integer> it=l.iterator();
		while(it.hasNext()) {
			it.next();
			it.remove();
		}*/
		System.out.println(l.toString());
		System.out.println(l.size());
	}

}
