package Algo;

import java.util.Comparator;

public class RicercaOrdinamento {
	
	public RicercaOrdinamento() {}

	public static <T> boolean ricercaLineare(T[] a, T e, Comparator<? super T> c) {
		for(int i=0; i<a.length; i++) {
			if(c.compare(a[i], e)>0)
				return false;
			if(c.compare(a[i], e)==0)
				return true;
		}
		return false;
	}
	
	public static <T> int ricercaBinaria(T[] a, T e, Comparator<? super T> c) {
		int inf=0;
		int sup=a.length-1;
		while(inf<=sup) {
			int med=(inf+sup)/2;
			if(a[med].equals(e))
				return med;
			if(c.compare(a[med], e)>0)
				sup=med-1;
			else
				inf=med+1;
		}
		return -1;
	}
	
	public static <T> boolean ricercaBinariaBool(T[] a, T e, Comparator<? super T> c) {
		int inf=0;
		int sup=a.length-1;
		while(inf<=sup) {
			int med=(inf+sup)/2;
			if(a[med].equals(e))
				return true;
			if(c.compare(a[med], e)>0)
				sup=med-1;
			else
				inf=med+1;
		}
		return false;
	}
	
	
	/*si prende un array, si cerca l'indice del minimo o del massimo quindi si scambia l'elemento
	 * a tale indice con il primo elemento (minimo) o con l'ultimo elemento (massimo) e si ripete il tutto
	 */
	/*//versione massimo
	public static <T extends Comparable<? super T>> void selectionSort(T[] a) {
		System.out.println(java.util.Arrays.toString(a));
		for(int i=a.length-1; i>=0; i--) {
			int iMax=i;
			for(int j=iMax; j>=0; j--) {
				if(a[j].compareTo(a[iMax])>0)
					iMax=j;
			}
			T tmp=a[i];
			a[i]=a[iMax];
			a[iMax]=tmp;
		}
		System.out.println(java.util.Arrays.toString(a));
	}*/
	
	//versione minimo
	public static <T extends Comparable<? super T>> void selectionSort(T[] a) {
	  System.out.println(java.util.Arrays.toString(a));
		for(int i=0; i<a.length; i++) {
			int iMin=i;
			for(int j=iMin; j<a.length; j++) {
				if(a[iMin].compareTo(a[j])>0)
					iMin=j;
			}
			T tmp=a[i];
			a[i]=a[iMin];
			a[iMin]=tmp;
		}
		System.out.println(java.util.Arrays.toString(a));
	}//selectionSort
	
	
	/*il bubble sort ordina un array considerando due elementi per volta, a[i] e a[i+1] oppure
 	a[i-1] e a[i] e scambia tali elementi se deve. compie al massimo n-1 scansioni per ordinare */
	//bubble ottimizzato per fermasi se gli scambi sono 0
	public static <T extends Comparable<? super T>> void bubbleSort(T[] a) {
		System.out.println(java.util.Arrays.toString(a));
		for(int j=a.length-1; j>=1; j--) {
		int scambi=0;
			for(int i=0; i<j; i++) {
				if(a[i].compareTo(a[i+1])>0) {
					T tmp=a[i+1];
					a[i+1]=a[i];
					a[i]=tmp;
					scambi++;
				}
			}
			if(scambi==0)
				break;
		}
		System.out.println(java.util.Arrays.toString(a));
	}
	
	/*//bubble sort ottimizzato con indice ultimo scambio
	public static <T extends Comparable<? super T>> void bubbleSort(T[] a) {
		System.out.println(java.util.Arrays.toString(a));
		int ius=0;
		for(int j=a.length-1; j>=1; j=ius) {
			int scambi=0;
			for(int i=0; i<j; i++) {
				if(a[i].compareTo(a[i+1])>0) {
					T tmp=a[i+1];
					a[i+1]=a[i];
					a[i]=tmp;
					scambi++;
					ius=i;
				}
			}
			if(scambi==0)
				break;
		}
		System.out.println(java.util.Arrays.toString(a));
	}//bubbleSort*/
	
	public static <T extends Comparable<? super T>> void insertionSort(T[] a) {
		System.out.println(java.util.Arrays.toString(a));
		for(int i=0; i<a.length; i++) {
			T x=a[i];
			for(int j=i-1; j>=0; j--) {
				if(a[j].compareTo(x)>0) {
					T tmp=a[j];
					a[j]=x;
					a[j+1]=tmp;
					System.out.println(java.util.Arrays.toString(a));
					System.out.println();
				}
			}
		}
		System.out.println(java.util.Arrays.toString(a));
	}
	
	
	/*dato un array e due limiti il merge divide a meta' ed ordina separatamente i due array 
	 *  dopodiche' fonde i due segmenti*/
	
	public static <T extends Comparable<? super T>> void mergeSort(T[] a, int inf, int sup) {
		if(inf<sup) {
		int med=(inf+sup)/2;
		mergeSort(a,inf, med);
		mergeSort(a,med+1, sup);
		merge(a,inf, med, sup);
		System.out.println(java.util.Arrays.toString(a));
		}
	}
	
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
	
	public static void main(String[] args) {
		Integer[] a= {3,71,8,0,1,10};
		mergeSort(a, 0, a.length-1);
	}
	
	
	
	
}
