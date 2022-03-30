package poo.esempi;

public class TestVarArg {
	
	public static int ricerca(int x, int...v) {
		for( int i=0; i<v.length; ++i )
			if( v[i]==x ) return i;
		return -1;
	}
	
	public static int max( int...a ) { //int...a dice che a è un vararg
		int m=a[0]; //variabile locale, DA INIZIALIZZARE SEMPRE A NOSTRO CARICO
		for( int i=1; i<a.length; ++i )
			if( a[i]>m ) m=a[i];
		return m;
	}//max
	
	public static void main( String...args ) {
		
		int v[]= {10,-4,12,23,-2,5}; //creazione implicita di array v
		
		int m1=max(14,20,54,22,-2,7,10,200,3); //metodo con un numero variabile di argomenti - vararg
		int m2=max(200,300);
		int m3=max( v );
		
		System.out.println("m1="+m1+" m2="+m2+" m3="+m3);
		
		int esito=ricerca( 23, v );
		System.out.println("esito="+esito);
		
	}//main
	
}//TestVarArg
