package poo.sistema;

import poo.util.Mat;

public class GaussDiagonale extends Gauss{
	public GaussDiagonale(double[][] m, double[] x) {
		super(m,x);
	}
	
	public void triangolarizza() {
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
	}
	
	public static void main(String[] args) {
		double[] [] m= {{}
				
		};
		GaussDiagonale g=new GaussDiagonale()
	}
	
	

}
