package poo.sistema;
import java.util.*;
import poo.util.*;
public class EsempioSistema {
	public static void main( String []args ){
		System.out.println("Risoluzione di un Sistema di Equazioni Lineari");
		Scanner sc=new Scanner( System.in );
		System.out.print("Dimensione del sistema=");
		int n=sc.nextInt();
		double [][]a=new double[n][n];
		double []y=new double[n];
		double []x=null;
		//lettura matrice
		System.out.println("Fornisci gli "+n+"x"+n+" elementi della matrice a per righe");
		for( int i=0; i<n; i++ )
			for( int j=0; j<n; j++ ) {
				System.out.print("a["+i+","+j+"]=");
				a[i][j]=sc.nextDouble();
			}
		System.out.println();
		System.out.println("Fornisci i(gli) "+n+" termini noti");
		for( int i=0; i<n; i++ ){
			System.out.print("y["+i+"]=");
			y[i]=sc.nextDouble();
		}
		Sistema s=new Cramer(a,y);
		System.out.println(s);
		try{
			x=s.risolvi();
		}catch( SistemaSingolare e ){
			System.out.println("Sistema Singolare!");
			System.exit(-1);
		}
		
		System.out.println(" ");

		
		System.out.println(s);
		//scrivi risultati
		System.out.println("Vettore delle incognite");
		for( int i=0; i<n; i++ )
			System.out.printf("x["+i+"]=%1.2f\n",x[i]);
	}//main
			
}
