package poo.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

public final class Array{
	
	private Array() {
	}
	
	//costruttore vuoto perche ' una classe di utilit� che non necessita la creazione di oggetti
	
	public static int ricercaLineare(Comparable[] a, Comparable x) {
		for(int i=0; i<a.length; i++)
			if(a[i].compareTo(x)>0)
				return i;
		return -1;
	}
	
	private static enum Op{ MERGE_SORT, MERGE };
	
	private static <T extends Comparable<? super T>> void mergeSortIte( T[] v, int inf, int sup ){
		class AD{
			int inf, med, sup;
			Op op;
			public AD( int inf, int med, int sup, Op op ) {
				this.inf=inf; this.med=med; this.sup=sup; this.op=op;
			}
		}//AD
		Stack<AD> stack=new StackConcatenato<>();
		//schedulazione della chiamata iniziale di mergeSort
		stack.push(new AD(inf,0,sup,Op.MERGE_SORT)); //med e' fittizio qui, posto a 0 come es.
		while( !stack.isEmpty() ) {
			AD ad=stack.pop(); //area dati corrente su cui eseguire le operazioni
			if( ad.op==Op.MERGE_SORT ) {
				if( ad.inf<ad.sup ) {
					ad.med=(ad.inf+ad.sup)/2;
					stack.push( new AD(ad.inf,ad.med,ad.sup,Op.MERGE) ); 
					//schedulazione MERGE
					stack.push( new AD(ad.med+1,0,ad.sup,Op.MERGE_SORT) ); 
					//schedulazione 2 chiamata di mergeSort
					stack.push( new AD(ad.inf,0,ad.med,Op.MERGE_SORT) ); 
					//schedulazione 1 chiamata di mergeSort
				}
			}
			else //ad.op==Op.MERGE
				merge( v, ad.inf, ad.med, ad.sup );
		}
	}//mergeSortIte
	
	public static <T extends Comparable<? super T>> void mergeSort( T[] v ) {
		mergeSort( v, 0, v.length-1 );
	}//mergeSort
	
	private static <T extends Comparable<? super T>> void mergeSort( T[] v, int inf, int sup ) {
		if( inf<sup ) {
			int med=(inf+sup)/2;
			mergeSort( v, inf, med ); //primo segmento
			mergeSort( v, med+1, sup ); //secondo segmento
			merge( v, inf, med, sup );
		}
	}//mergeSort 
	
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<? super T>> void merge( T[] v, int inf, int med, int sup ) {
		T[] aux=(T[])new Comparable[sup-inf+1];
		int i=inf, j=med+1, k=0;
		while( i<=med && j<=sup ) {
			if( v[i].compareTo(v[j])<0 ) {
				aux[k]=v[i]; 
				i++; 
				k++;
			}
			else {
				aux[k]=v[j];
				j++; 
				k++;
			}
		}
		//gestione residuo
		while( i<=med ) {
			aux[k]=v[i]; 
			i++; 
			k++;
		}
		while( j<=sup ) {
			aux[k]=v[j]; 
			j++; 
			k++;
		}
		for( k=0; k<aux.length; ++k )
			v[inf+k]=aux[k];
	}//merge
	
	public static <T> int ricercaBinaria( T[] a, T e , Comparator<? super T> c) {
		//PRE V e ordinato per valori crescenti
		int inf=0; 
		int sup=a.length-1;
		while( inf<=sup ){
			int med=(inf+sup)/2;
			if(a[med].equals(e))
				return med;
			if( c.compare(a[med], e)>0 ) 
				sup=med-1;
			else 
				inf=med+1;
		}
		return -1;
	}//ricercaBinaria
	
	public static <T extends Comparable<? super T>> int ricercaBinaria( T[] a, T e ) {
		//PRE V e ordinato per valori crescenti
		int inf=0, sup=a.length-1;
		while( inf<=sup ){
			int med=(inf+sup)/2;
			if(a[med].equals(e))
				return med;
			if( a[med].compareTo(e)>0 ) 
				sup=med-1;
			else inf=med+1;
		}
		return -1;
	}//ricercaBinaria
	
	public static <T extends Comparable<? super T>> int ricercaBinaria( Vector<T> v, T e ) {
		//PRE V � ordinato per valori crescenti
		int inf=0, sup=v.size()-1;
		while( inf<=sup ){
			int med=(inf+sup)/2;
			if(v.get(med).equals(e))
				return med;
			if( v.get(med).compareTo(e)>0 ) 
				sup=med-1;
			else inf=med+1;
		}
		return -1;
	}//ricercaBinaria
	
	
	public static <T extends Comparable<? super T>> void selectionSort(T[] a) {
		for(int i=0; i<a.length; i++) {
			int iMin=i;
			for(int j=i+1; j<a.length; j++) {
				if(a[iMin].compareTo(a[j])>0)
					iMin=j;
			}
			T tmp=a[i];
			a[i]=a[iMin];
			a[iMin]=tmp;
		}
	}
	
	public static <T extends Comparable<? super T>> void selectionSort(List<T> l) {
		Iterator<T> it=l.iterator();
		T min=it.next();
		while(it.hasNext()) {
			T x=it.next();
			
		}
	}
	
	public static <T> void selectionSort(T[] a, Comparator<?super T> c) {
		for(int i=0; i<a.length; i++) {
			int iMin=i;
			for(int j=i+1; j<a.length; j++) {
				if(c.compare(a[iMin], a[j])>0)
					iMin=j;
			}
			T tmp=a[i];
			a[i]=a[iMin];
			a[iMin]=tmp;
		}
	}
	
	public static <T extends Comparable<? super T>> void bubbleSort(T[] a){
		int ius=0; //indice dell'ultimo scambio in modo da fermarmi subito e non verificare un'altra volta l'array per inter
		for(int j=a.length-1; j>0; j=ius) {
			int scambi=0;
			for(int i=0; i<j; i++) {
				if(a[i].compareTo(a[i+1])>0) {
					T tmp= a[i];
					a[i]=a[i+1];
					a[i+1]=tmp;
					scambi++;
					ius=i;
				}
			}
			if(scambi==0)
				break;
		}
	}
	
	public static <T> void bubbleSort(T[] a, Comparator<? super T> c) {
		int ius=0; //indice dell'ultimo scambio in modo da fermarmi subito e non verificare un'altra volta l'array per inter
		for(int j=a.length-1; j>0; j=ius) {
			int scambi=0;
			for(int i=0; i<j; i++) {
				if(c.compare(a[i], a[i+1])>0) {
					T tmp= a[i];
					a[i]=a[i+1];
					a[i+1]=tmp;
					scambi++;
					ius=i;
				}
			}
			if(scambi==0)
				break;
		}
	}
	
	
	
	public static <T extends Comparable<? super T>> void insertionSort(T[] v) {
		for(int i=1; i<v.length; i++) {
			T x= v[i];
			int j=i;
			while(j>0 && v[j-1].compareTo(x)>0) {
				v[j]=v[j-1];
				j--;
			}
			v[j]=x;
		}
	}//insertionSort
	
	public static <T> void insertionSort(T[] a, Comparator<? super T> c) {
		//Alternativa utilizzando comparator, dove in questo caso T non ha il compareTo
		for(int i=1; i<a.length; i++) {
			T x= a[i];
			int j=i;
			while(j>0 && c.compare(a[j-1], x)>0) {
				a[j]=a[j-1];
				j--;
			}
			a[j]=x;
		}
	}//insertionSort
	
	public static void CountingSort(int[]v, int size) {
		int[] ret = new int[size + 1];
		int max = v[0];
		int min=v[0];
		for (int i = 1; i < size; i++) {
			if (v[i] > max)
				max = v[i];
			if(v[i]<min)
				min=v[i];
		}
		int[] indici = new int[max-min + 1];
		for (int i = 0; i < size; i++)
		    indici[v[i]]++;
		for (int i = 1; i <= max; i++)
		     indici[i] += indici[i - 1];
		for (int i = size - 1; i >= 0; i--) {
			ret[indici[v[i]] - 1] = v[i];
		    indici[v[i]]--;
		}
		for (int i = 0; i < size; i++)
		    v[i] = ret[i];
	}
	
	
	public static <T extends Comparable<? super T>> void heapSort(T[] a){
		Heap<T> heap= new Heap<>(a.length);
		//prima fase:caricamento heap
		for(int i=0; i<a.length; i++)
			heap.add(a[i]); //heap disordinato ma con minimo nella radice
		int j=0; //posizione prossimo elemento vuoto di a
		//seconda fase:svuotamento heap
		while(heap.size()>0) {
			a[j]=heap.remove();//tolgo ordinatamente il minimo dall'heap cosicche ho un array ordinato
			j++;
		}
	}//heapSort O(n log n)
	
	
	public static void main(String[] args) {
		//esempio lambda expression
		Integer[] v= {8,2,9,0,12,1};
		Integer[] a= {90,1,73,69,3,6,18,7,2};
		System.out.println(Arrays.toString(a));
		/*selectionSort(a, (Integer i1, Integer i2)->{
			//il tipo di i1 e i2 viene inferito dal compilatore quindi, potrebbe essere omesso
			if(i1<i2)
				return -1;
			if(i1==i2)
				return 0;
			return 1;
			//return i1-i2 e molto piu efficente e ritorna lo stesso risultato degli if
			//se inverto i1<i2 oppure faccio return i2-i1 allora avra' un ordinamento decrescente
		});*/
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(v));
		selectionSort(v, (i1,i2)->{
			return i1-i2;
		});
		System.out.println(Arrays.toString(v));
		int x=ricercaBinaria(v, 12);
		System.out.println(Arrays.toString(v));
		heapSort(v);
		System.out.println(Arrays.toString(v));
	}
	
}


