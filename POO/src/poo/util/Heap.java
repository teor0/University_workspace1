package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Heap<T extends Comparable<? super T>> implements Iterable<T>{
	private T[] heap; //usa gli indici da 1 fino a size
	private int n, size; //size indica l'ultimo occupato. n indica la dimensione

	public Heap() {
		this(17);
	}
	
	@SuppressWarnings("unchecked")
	public Heap(int n) {
		if(n<=0)
			throw new IllegalArgumentException();
		this.n=n;
		size=0;
		heap=(T[]) new Comparable[n+1]; //n+1 perche si parte dalla posizione 1
	}
	
	public int size() {
		return size;
	}
	
	public boolean contains(T x) {
		if(size==0|| heap[1].compareTo(x)<0) //incrementa l'efficenza del contains
			return false;
		for(int i=1; i<=size; i++) 
			if(heap[i].equals(x))
				return true;
		return false;
	}

	
	public void clear() {
		for(int i=1; i<=size; i++)
			heap[i]=null;
		size=0;
	}//clear
	
	
	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder(100);
		sb.append("[");
		for(int i=1; i<=size; i++) {
			sb.append(heap[i]);
			if(i<size)
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
	
	public void crea(T[] a) {
		for(T el:a)
			this.add(el);
	}
	
	public void add(T x) {
		if(size==n) {//array pieno quindi raddoppiamo la capacita'
			heap=java.util.Arrays.copyOf(heap, 2*n+1);
			n=2*n;
		}
		size++;
		heap[size]=x;
		int i=size;
		while(i>1){
			//while per aggiustare l'heap
			if(heap[i].compareTo(heap[i/2])<0) { //confronto tra figlio e padre
				T tmp=heap[i];
				heap[i]=heap[i/2];
				heap[i/2]=tmp;
				i=i/2; //per efficienza si continua da i/2
			}
			else
				break;
		}
	}//add costa O(log n)
	
	//toglie il minimo
	public T remove(){
		if(size==0)
			throw new NoSuchElementException();
		T min= heap[1]; //radice dell'albero heap
		heap[1]=heap[size];
		heap[size]=null;
		size--;
		//fix downward
		int i=1;
		while(i<=size/2) {
			//while fino a size/2 perche' dopo size/2 2i andrebbe fuori dall'array
			//figli di heap[i]
			int j=2*i;
			int k=j+1;
			//trova il minimo dei 2 figli, prima lo assegno a j e poi controllo 
			//se l'elemento ad indice k non sia minore
			int z=j; //prima ipotesi del figlio piu piccolo
			if(k<=size && heap[k].compareTo(heap[z])<0)
				z=k;
			if(heap[i].compareTo(heap[z])>0) {
				T tmp=heap[i];
				heap[i]=heap[z];
				heap[z]=tmp;
				i=z;
			}
			else
				break;
		}
		return min;
	}//remove costa O(log n)
	
	public void remove( T x ) { //rimozione di un elemento in una posizione qualsiasi
		//scardina l'heap in modo radicale
		int i=1; //0 e' una cella vacante
		while( i<=size ) {//cerco l'elemento da rimuovere
			if( heap[i].equals(x) ) 
				break;
			else 
				i++;
		}
		if( i>size ) 
			return;//non ho trovato l'elemento
		heap[i]=null; //toglie x
		int limite=size;
		size=i-1; 
		//virtualmente sono tolti tutti gli elementi da i+1 in poi
		//re-inserire tutti gli elementi da i+1 a limite
		for( int j=i+1; j<=limite; ++j ) {
			T y=heap[j]; 
			heap[j]=null;
			add( y ); //stessa cosa se fosse stato add(heap[j])
		}
	}//remove costa O(n)
	
	public int hashCode() {
		int M=83;
		int h=0;
		if(size==0)
			throw new IllegalStateException();
		for(int i=1; i<=size; i++)
			h+=h*M+heap[i].hashCode();
		return h;
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Heap))
			return false;
		if(this==o)
			return true;
		Heap<T> hp=(Heap) o;
		if(hp.size!=this.size)
			return false;
		for(int c=0; c<=hp.size; c++)
			if(!hp.heap[c].equals(this.heap[c]))
				return false;
		return true;
	}
	
	public Iterator<T> iterator(){
		return new HeapIterator();
	}
	
	private class HeapIterator implements Iterator<T>{
		private int cor=0;
		public boolean hasNext() {
			if(cor==0)
				return size>0;
			return cor<size;
		}
		
		public T next() {
			if(!hasNext())
				throw new NoSuchElementException();
			cor++;
			return heap[cor];
		}
		
		public void remove() {
			Heap.this.remove(heap[cor]);
			cor--;
		}
	}
	
	public static void main(String[] args) {
		Heap<Integer> hp=new Heap<>(5);
		Integer[] a= {23,-3,10,2,5,1,-4,12,7,6};
		hp.crea(a);
		/*hp.add(1);
		hp.add(3);
		hp.add(5);
		hp.add(6);
		hp.add(7);*/
		System.out.println(hp.contains(1));
		//hp.remove(5);
		/*Iterator<Integer> it= hp.iterator();
		it.next();
		it.next();
		it.next();
		it.remove();
		it.next();
		it.remove();*/
		System.out.println(hp);
	}
	
}
