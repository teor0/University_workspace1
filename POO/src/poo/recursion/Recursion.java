package poo.recursion;

import java.util.NoSuchElementException;

public final class Recursion {
	private Recursion() {}
	
	public static boolean palindroma( String s ) {//anna
		if( s.length()<=1 ) return true;
		if( s.charAt(0)!=s.charAt(s.length()-1) ) return false;
		return palindroma( s.substring(1,s.length()-1) );  //tail recursion
	}//palindroma
	
	public static boolean palindromaIte( String s ) {
		int i=0, j=s.length()-1;
		while( i<j ) {
			if( s.charAt(i)!=s.charAt(j) ) return false;
			i++; j--;
		}
		return true;
	}//palindromaIte
	
	public static void scriviInverso( int n ) {
		//se n=173 occorre scrivere su output 371, con un algoritmo ricorsivo
		if(n<0)
			throw new IllegalArgumentException();
		inverso(n);
	}
	
	private static void inverso(int n) {
		if(n<10)
			System.out.println(n);
		else {
			System.out.print(n%10);
			inverso(n/10);
		}
	}
	
	public static void scriviInversoIte( int n ) {
		//se n=173 occorre scrivere su output 371, con un algoritmo iterativo
		while(true) {
		if(n<10) {
			System.out.println(n);
			break;
		}
		System.out.print(n%10);
		n=n/10;
		}
	}
	
	public static int max( int[] v ) {
		if( v==null || v.length==0 ) throw new IllegalArgumentException();
		return max( v, 0, v.length-1 );
	}//max
	
	private static int max( int[]v, int inf, int sup ) { 
		if( inf==sup ) return v[inf];
		int med=(inf+sup)/2;
		int m1=max( v, inf, med ); //m1 e' il max del primo segmento
		int m2=max( v, med+1, sup ); //m2 è il max del secondo segmento
		return m1>m2?m1:m2;
	}//max
	
	public static int somma( int[]v ) {
		if( v==null || v.length==0 ) 
			throw new IllegalArgumentException();
		return somma( v, 0, v.length-1);
	}
	
	public static int somma( int[] v, int inf, int sup ) {
		if(v[inf]==v[sup])
			return v[inf];
		int med=(inf+sup)/2;
		int s1=sum(v,med+1,sup,0);
		int s2=sum(v,inf,med,0);
		return s1+s2;
	}
	
	private static int sum(int[] v,int i, int j, int s){
		if(i==j)
			return s+v[i];
		s+=v[i];
		return sum(v,i+1, j, s);
	}
	
	public static <T extends Comparable<? super T>> int ricercaBinaria( T[]v, T x ) {
		return ricercaBinaria(v,x,0,v.length-1);
	}//ricercaBinaria
	
	private static <T extends Comparable<? super T>> int ricercaBinaria( T[] v, T x, int inf, int sup ) {
		if(sup<inf)
			return -1;
		int med=(inf+sup)/2;
		if(v[med].equals(x))
			return med;
		if(v[med].compareTo(x)>0) {
			sup=med-1;
			return ricercaBinaria(v,x,inf,sup);
		}
		else {
			inf=med+1;
			return ricercaBinaria(v,x,inf,sup);
		}
	}//ricercaBinaria
	
	public static int fattoriale(int n) {
		if(n==0||n==1)
			return 1;
		return n*fattoriale(n-1);
	}
	
	public static void main( String[] args ) {
		String s="radar";
		if( palindroma(s) ) System.out.println(s+" e' palindroma.");
		else System.out.println(s+" non e' palindroma.");
		
		int a[]= {2,5,1,-1,4,7};
		Integer[] v= {0,1,2,3,4,5,6,8,20,30,40,41,59,60,69,70,900};
		System.out.println("max("+java.util.Arrays.toString(a)+")="+max(a));
		Integer r= ricercaBinaria(v,-4);
		System.out.println("Contiene "+r);
		int k=371;
		int l=371;
		scriviInverso(k);
		scriviInversoIte(l);
		System.out.println("fatto: " + fattoriale(5));
		System.out.println("somma:" +somma(a));
	}
}//Recursion
