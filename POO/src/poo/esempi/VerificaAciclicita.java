package poo.esempi;
 
import poo.grafo.*;
import java.util.*;

public class VerificaAciclicita {
	public static <N> boolean aciclico( GrafoOrientato<N> go ) {
		if( go==null ) throw new IllegalArgumentException();
		GrafoOrientato<N> g=(GrafoOrientato<N>)go.copia();
		boolean riducibile=true;
		while( riducibile ) {
			riducibile=false; //pessimismo
			Set<N> rimossi=new HashSet<>();
			for( N n: g ) {
				if( g.gradoEntrata(n)==0 ) {
					riducibile=true;
					rimossi.add(n);
				}
			}
			while( rimossi.size()!=0 ) {
				Iterator<N> it=rimossi.iterator();
				N n=it.next(); it.remove();
				g.rimuoviNodo(n);
			}
		}
		return g.numNodi()==0;
	}//aciclico
	public static void main( String[] args ) {
		GrafoOrientato<Integer> g=new GrafoOrientatoImpl<>();
		g.insNodo(1);
		g.insNodo(2);
		g.insNodo(3);
		g.insNodo(4);
		g.insArco(1,2);
		g.insArco(2,3);
		g.insArco(3,4);
		System.out.println(g);
		System.out.println("Grafo aciciclo? "+aciclico(g));
	}//main
}
