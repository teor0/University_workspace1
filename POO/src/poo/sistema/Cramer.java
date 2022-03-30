package poo.sistema;
import poo.util.*;
public class Cramer extends Sistema {
	private double[][] m;
	private double[] x;
	public Cramer(double[][] m, double[] y) {
		super(m,y);
		this.m=new double[m.length][m.length+1];
		for(int i=0; i<m.length; i++) {
			System.arraycopy(m[i], 0, this.m[i], 0, m.length);
			this.m[i][m.length]=y[i];
		}
		this.x=new double[y.length];
		System.arraycopy(y, 0, x, 0, x.length);
	}
	
	private double determinante(double [][] m){
		double ret=0;
		if(m.length==2){
			ret+=m[0][0]*m[1][1];
			ret+=-(m[0][1]*m[1][0]);
			return ret;
		}
		ret+=m[0][0]*m[1][1]*m[2][2];
		ret+=m[0][1]*m[1][2]*m[2][0];
		ret+=m[0][2]*m[1][0]*m[2][1];
		ret-=m[0][0]*m[1][2]*m[2][1];
		ret-=m[0][1]*m[1][0]*m[2][2];
		ret-=m[0][2]*m[1][1]*m[2][0];
		return ret;
	}
	
	private double[][] sostituisci(double x[], int i){
		int n=getN();
		double [][]ret= new double[n][n];
		for( int k=0; k<m.length; k++ )
			for( int j=0; j<m.length; j++ )
				ret[k][j]=m[k][j];
		for(int t=0; t<ret.length; t++) {
			ret[t][i]=x[t];
		}
		return ret;
	}
	
	private double [] risoluzione() {
		int n=getN();
		double [] ret= new double[n];
		double det=determinante(m);
		double [] x = new double [n];
		for(int j=0; j<x.length; j++)
			System.arraycopy(this.x, 0, x, 0, x.length);
		if(Mat.sufficientementeProssimi(det, 0.0D))
			throw new RuntimeException("Determinante nullo, sistema non risolvibile");
		if(n==2) {
			double Dx=0;
			double Dy=0;
			Dx+= x[0]*m[1][1];
			Dx-= m[0][1]*x[1];
			Dy+= m[0][0]*x[1];
			Dy-= x[0]*m[1][0];
			ret[0]=Dx/det;
			ret[1]=Dy/det;
			return ret;
		}
		double[][] mx =sostituisci(x,0);
		double[][] my =sostituisci(x,1);
		double[][] mz =sostituisci(x,2);
		double Dx=determinante(mx);
		double Dy=determinante(my);
		double Dz=determinante(mz);
		ret[0]= Dx/det;
		ret[1]= Dy/det;
		ret[2]= Dz/det;
		return ret;
	}
	
	
	public double[] risolvi() {
		return risoluzione();
	}
	

	public String toString(){
		StringBuilder sb=new StringBuilder(500);
		for( int i=0; i<m.length; i++ ){
			for( int j=0; j<=m.length; j++ ){
				sb.append( String.format("%1.2f", m[i][j]) );
				sb.append(' ');
			}
			sb.append('\n');
		}
		return sb.toString();
	}

}
