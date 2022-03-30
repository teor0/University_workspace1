package poo.esempi;
import poo.geometria.*;
import poo.razionali.Razionale;
public class TestObject {
	public static void main( String...args ) {
		Object []v=new Object[6];
		
		//esempi di assegnamento da particolare a generale
		v[0]="Java is fantastic!";
		v[1]=new Punto(3,5);
		v[2]=new Razionale(12,18);
		v[3]=new Triangolo(new Punto(),new Punto(4,7),new Punto(0,-3));
		v[4]=new Disco(4,6,9);
		v[5]=new int[] {2,6,3,1,9};
		
		for( int i=0; i<v.length; ++i ) {
			if( v[i] instanceof Punto ) {
				((Punto)v[i]).sposta(10,-3);
				System.out.println(v[i]);
			}
		}
		
		for( int j=0; j<v.length; ++j )
			System.out.println(v[j]);
		
		System.out.println( java.util.Arrays.toString(v) );
		
	}
}
