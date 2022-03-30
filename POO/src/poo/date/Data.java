package poo.date;
import java.util.*;
public class Data implements Comparable<Data>{
	private final int G, M, A;

	public enum Cosa { 
		GIORNO, MESE, ANNO 
		}

	public Data(){
		GregorianCalendar gc=new GregorianCalendar();
		G=gc.get( GregorianCalendar.DAY_OF_MONTH );
		M=gc.get( GregorianCalendar.MONTH )+1;
		A=gc.get( GregorianCalendar.YEAR );
	}//Data
	public Data( int g, int m, int a ){
		if( a<0 || m<1 || m>12 || g<1 || g>durataMese(m,a) )
			throw new IllegalArgumentException();
		this.G=g; this.M=m; this.A=a;
	}//Data
	public Data( Data d ){
		G=d.G; M=d.M; A=d.A;
	}//Data

	public int get( Cosa x ){
		switch( x ){
			case GIORNO: return G;
			case MESE: return M;
			default : return A;
		}
	}//get

	public static boolean bisestile( int a ){
		if( a<0 )
			throw new IllegalArgumentException();
		if( a%4!=0 ) return false;
		if( a%100==0 && a%400!=0 ) return false;
		return true;
	}//bisestile
	
	public static int durataMese( int m, int a ){
		if( m<1 || m>12 || a<0 )
			throw new IllegalArgumentException();
		int durata;
		switch( m ){
			case 1: case 3: case 5: case 7: case 8:
			case 10: case 12: durata=31; break;
			case 2: durata=bisestile(a) ? 29:28; break;
			default: durata=30;
		}//switch
		return durata;
	}//durataMese

	public Data giornoDopo(){
		int durata=durataMese( M, A );
		int g1, m1, a1;
		if( G==durata ){
			g1=1;
			if( M==12 ){
				m1=1; a1=A+1; 
			}
			else{ 
				m1=M+1; a1=A; 
			}
		}
		else{ 
			g1=G+1; m1=M; a1=A; 
		}
		return new Data( g1,m1,a1 );
	}//giornoDopo

	public Data giornoPrima(){
		int g1, m1,a1;
		if(G==1 && M==1 && A==0){
			throw new IllegalArgumentException();
		}
		if(G==1 && M==1){
			g1=31;
			m1=12;
			a1=A-1;
			return new Data(g1,m1,a1);
		}
		if(G==1){
			m1=M-1;
			int durata=durataMese(m1,A);
			g1=durata;
			a1=A;
		}
		else{
			g1=G-1;
			m1=M;
			a1=A;
		}
		return new Data(g1,m1,a1);
	}//giornoPrima

	public int distanza(Data d){
		Data d1=new Data(this);
		Data d2= new Data(d);
		if(d1.compareTo(d2)==0)
			return 0;
		if(d1.compareTo(d2)>0){
			Data tmp=d1;
			d1=d2;
			d2=tmp;
		}
		int con=0;
		do{
			con++;
			d1=d1.giornoDopo();
		}	
		while(d1.compareTo(d2)<0);
		return con;
	}//distanza
	@Override
	public String toString(){
		String s="";
		if(G<10) s=s+"0";
		s=s+G+"/";
		if(M<10) s=s+"0";
		s=s+M+"/"+A;
		return s;
	}//toString
	
	
	public int compareTo(Data d){
		if(this.A<d.A || this.A==d.A && this.M<d.M || this.A==d.A && this.M==d.M && this.G<d.G)
			return -1;
		if(this.A==d.A && this.M==d.M && this.G==d.G)
			return 0;
		return 1;
	}
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Data))
			return false;
		if(o==this)
			return true;
		Data d= (Data) o;
		return this.A==d.A && this.M==d.M && this.G==d.G;
	}
	
	public static void main(String[] args){
		Data d=new Data(7, 11, 2000);
		Data f=new Data(1, 1, 2000);
		Data g=new Data(1,9,1842);
		Data k=d.giornoPrima();
		System.out.println(d);
		System.out.println(k);
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(f.compareTo(g));
		System.out.println(f.giornoPrima());
		System.out.println(g.giornoPrima());
	}
}//Data