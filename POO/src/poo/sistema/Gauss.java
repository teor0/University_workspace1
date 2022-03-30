package poo.sistema;
import poo.util.*;
public class Gauss extends Sistema {
	protected double[][] a;
	
	public Gauss( double[][] a, double[] y ) {
		super(a,y);
		this.a=new double[a.length][a.length+1];
		for( int i=0; i<a.length; ++i ) {
			System.arraycopy(a[i], 0, this.a[i], 0, a.length);
			this.a[i][a.length]=y[i];
		}
	}
	
	protected void triangolarizza() {
		int n=getN();
		for( int j=0; j<n; j++) {
			//a[j][j] e l'elemento diagonale o di pivot
			//ipotesi: a[j][j] e diverso da 0
			
			if( Mat.sufficientementeProssimi(a[j][j], 0.0D) ) { //occorre colmare la lacuna
				//fai pivoting
				int p=j+1;
				for(; p<n; p++)
					if( !Mat.sufficientementeProssimi(a[p][j], 0.0D) )//trovato nuovo pivot
						break;
				if( p==n ) 
					throw new RuntimeException("Sistema Singolare!");
				//scambia riga j-esima con la riga p-esima
				
				double[] tmp=a[j];
				a[j]=a[p]; 
				a[p]=tmp;
			}
			//azzera tutti i coefficienti sulla colonna j-esima, sulle righe j+1 fino a n-1
			for( int i=j+1; i<n; i++) {
				if( !Mat.sufficientementeProssimi(a[i][j],0.0D) ){
					//azzera il coeff a[i][j] che non e gia zero
					double cm=a[i][j]/a[j][j]; //coeff moltiplicativo
					//dalla riga i-esima, sottrai la riga j-esima moltiplicata per cm
					//fai combinazione lineare, 
					//tenendo presente che a sinistra di j, gli elementi sono gi� nulli
					for( int k=j; k<=n; ++k ) {
						a[i][k]=a[i][k]-a[j][k]*cm;
					}	
				}
			}
			
			for( int i=j-1; i>=0; i--) {
				if( !Mat.sufficientementeProssimi(a[i][j],0.0D) ){
					double cm=a[i][j]/a[j][j]; 
					for( int k=j; k<=n; ++k ) {
						a[i][k]=a[i][k]-a[j][k]*cm;
					}	
				}
			}
		}//FOR PER LA RIDUZIONE SOPRA
		
	}//triangolarizza
	
	protected double[] calcolaSoluzione() {
		int n=getN();
		double[] x=new double[n];
		for( int i=n-1; i>=0; i-- ) {//per tutte le equazioni dall'ultima verso la prima
			double sm=a[i][n];
			//portiamo a secondo membro tutti i termini relativi ad incognite gi� calcolate
			for( int j=i+1; j<n; j++ ) //ciclo saltato quando i=n-1
				sm=sm-a[i][j]*x[j];
			x[i]=sm/a[i][i];
		}
		return x;
	}//calcolaSoluzione
	
	public double[] risolvi() {
		triangolarizza(); //la matrice dei coeff + termini noti
		return calcolaSoluzione();
	}//risolvi
	
	public String toString(){
		StringBuilder sb=new StringBuilder(500);
		for( int i=0; i<a.length; i++ ){
			for( int j=0; j<=a.length; j++ ){
				sb.append( String.format("%1.2f", a[i][j]) );
				sb.append(' ');
			}
			sb.append('\n');
		}
		return sb.toString();
	}//toString

}
