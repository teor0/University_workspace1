package poo.util;
import java.util.*;
public final class Matrix {
	
	private Matrix() {}
	
	public static double[][] add( double[][] a, double[][] b ){
		//PRE: a, b matrici rettangolari compatibili
		if( a.length!=b.length || a[0].length!=b[0].length )
			throw new IllegalArgumentException("Matrici incompatibili per l'addizione");
		double[][] somma=new double[a.length][a[0].length];
		for( int i=0; i<a.length; ++i )
			for( int j=0; j<a[0].length; ++j )
				somma[i][j]=a[i][j]+b[i][j];
		return null;
	}//add
	
	public static int[][] add( int[][] a, int[][] b ){
		//PRE: a, b matrici rettangolari compatibili
		if( a.length!=b.length || a[0].length!=b[0].length )
			throw new IllegalArgumentException("Matrici incompatibili per l'addizione");
		int[][] somma=new int[a.length][a[0].length];
		for( int i=0; i<a.length; ++i )
			for( int j=0; j<a[0].length; ++j )
				somma[i][j]=a[i][j]+b[i][j];
		return null;
	}//add	
	//sviluppare per esercizio altri metodi di utilità sull'aritmetica di matrici
	
	public static double[][] trasposta(double[][] a){
		//PRE: a è quadrata
		if(a.length!=a[0].length)
			throw new IllegalArgumentException("Matrice non quadrata!");
		double [][] m= new double[a.length][a.length];
		for(int i=0; i<a.length; i++)
			for(int j=0; j<a.length; j++) {
				if(i!=j)
					m[j][i]=a[i][j];
				else
					m[i][j]=a[i][j];
			}
		return m; 
	}//trasposta
	
	public static double[][] mul( double[][] a, double[][] b  ){
		//PRE: a e b quadrate e della stessa dimensione
		if(a.length!=b.length || a.length!=a[0].length || b.length!=b[0].length)
			throw new IllegalArgumentException("Matrici incompatibili per la moltiplicazione");
		double [][] ret=new double[a.length][a.length];
		int i=0;
		while(i<a.length) {
			for(int j=0; j<a.length; j++) {
				double somma=0.0D;
				for(int k=0; k<a.length; k++) {
					somma+=a[i][k]*b[k][j];
				}
				ret[i][j]=somma;
			}
			i++;	
		}
		return ret;
		
	}//mul
	
	public static double determinante( double[][] m ) {//m è attesa matrice quadrata
		for( int i=0; i<m.length; ++i )
			if( m.length!=m[i].length )
				throw new IllegalArgumentException("Matrice non quadrata!");
		double[][] a=new double[m.length][m.length];
		//copia di m su a
		for( int i=0; i<a.length; ++i )
			for( int j=0; j<a.length; ++j )
				a[i][j]=m[i][j];
		int n=a.length, scambi=0; //attenzione alle inizializzazioni
		for( int j=0; j<n; ++j ) {
			if( Mat.sufficientementeProssimi(a[j][j], 0.0D) ) {//fai pivoting
				int p=j+1;
				for( ; p<n; ++p )
					if( !Mat.sufficientementeProssimi(a[p][j], 0.0D) ) break;
				if( p==n ) return 0.0D; //determinante zero
				double[] tmp=a[j]; a[j]=a[p]; a[p]=tmp;
				scambi++;
			}
			for( int i=j+1; i<n; ++i ) {//ciclo di azzeramento sotto a[j][j]
				if( !Mat.sufficientementeProssimi(a[i][j], 0.0D) ) {
					double cm=a[i][j]/a[j][j];
					for( int k=j; k<n; ++k ) {
						a[i][k]=a[i][k]-a[j][k]*cm;
					}
				}
			}
		}
		double d=1.0D;
		for( int i=0; i<n; ++i )
			d=d*a[i][i];
		if( scambi%2!=0 ) d=d*(-1);
		return d;
	}//determinante
	
	//USAMI
	public static StringBuilder stampa(double[][] m) {
		StringBuilder sb=new StringBuilder(50);
		for( int i=0; i<m.length; i++ ){
			for( int j=0; j<m.length; j++ ){
				sb.append( String.format("%1.2f", m[i][j]));
				sb.append(' ');
			}
			sb.append('\n');
		}
		return sb;
	}
	
	public static void main( String[] args ) {//demo
		double[][] m= {
			{1,2},
			{3,2}
		};
		double [][] t= {
				{4,2},
				{5,1}
		};
		StringBuffer sb= new StringBuffer(200);
		for(int i=0; i<m.length; i++) {
			for(int k=0; k<m.length; k++) {
				sb.append(String.format("%1.2f", m[i][k]));
				sb.append(' ');
			}	
			sb.append("\n");
		}
		System.out.println(sb);
		m=Matrix.mul(m,t);
		
		StringBuffer sb1= new StringBuffer(200);
		for(int i=0; i<m.length; i++) {
			for(int k=0; k<m.length; k++) {
				sb1.append(String.format("%1.2f", m[i][k]));
				sb1.append(' ');
			}	
			sb1.append("\n");
		}
		System.out.println(sb1);
	}//main
	

}
