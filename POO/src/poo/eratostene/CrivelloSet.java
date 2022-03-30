package poo.eratostene;
import java.util.*;
public class CrivelloSet extends CrivelloAstratto {
	//tre soluzioni
	//1a: si usano due TreeSet
	//2a: si usa solo un TreeSet e si migliora il processo di identificazione dei primi (for)
	//3a: si usa solo un LinkedHashSet che ricorda l'ordine di inserimento degli elementi, da 2 ad N
	
	private Set<Integer> crivello=new LinkedHashSet<>();
	//private Set<Integer> primi=new TreeSet<>();
	private final int N;
	
	public CrivelloSet( final int N ) {
		if( N<=1 ) 
			throw new IllegalArgumentException();
		for( int i=2; i<=N; ++i ) 
			crivello.add(i);
		this.N=N;
	}
	public Iterator<Integer> iterator(){ 
		return crivello.iterator(); 
	}
	
	public void filtra() {
/*
		Iterator<Integer> it=null;
		while( !crivello.isEmpty() ) {
			it=crivello.iterator();
			//estrazione del minimo dal crivello - it.hasNext() necessariamente true
			int min=it.next();
			primi.add( min );
			//rimozione di tutti i multipli di m
			int m=min;
			while( m<=N ) {
				crivello.remove(m);
				m=m+min;
			}
		}//while
*/
		for( int x=2; x<=Math.round(Math.sqrt(N)); x=(x==2)?x+1:x+2 ){
			if( crivello.contains(x) ) {//x � primo ed � minimo
				//elimina i multipli di x
				int m=x+x;
				while( m<=N ) {
					crivello.remove(m);
					m=m+x;
				}
			}
		}
	}//filtra
	
	public static void main( String []args ) {
		Crivello c=new CrivelloSet(100);
		c.filtra();
		Crivello c2=c;
		Crivello d=new CrivelloSet(100);
		Crivello f= new CrivelloSet(50);
		f.filtra();
		System.out.println(c2);
		System.out.println(f);
		System.out.println(f.equals(c));
		System.out.println(f.equals(d));
		System.out.println(d.hashCode());
		System.out.println(c.equals(d));
		System.out.println(c.equals(c2));
		System.out.println(c.hashCode());
	}//main
	
}//CrivelloSet
