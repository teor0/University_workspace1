package poo.esempi;
import java.util.*;



public class TestList {
	
	public static void main( String[] args ) {
		
		class MioComparatore implements Comparator<String>{ //in java.lang
			public int compare( String s1, String s2 ) {
				if( s1.length()<s2.length() ) 
					return -1;
				if( s1.length()>s2.length() ) 
					return 1;
				return s1.compareTo(s2);
			}//compare
		}//MioComparatore
		
		List<String> ls=new ArrayList<>( Arrays.asList("fuoco","ugo","casa","tana","zeno","alberello","dado","abaco") ) ;
		System.out.println(ls);
		Collections.sort( ls );
		System.out.println(ls);
		Collections.sort( ls, new MioComparatore() );
		System.out.println(ls);
		
		Collections.sort( ls, new Comparator<String>(){ //estensione istanziazione "al volo" di una classe anonima
			public int compare( String s1, String s2 ) {
				if( s1.length()<s2.length() ) return -1;
				if( s1.length()>s2.length() ) return 1;
				return s1.compareTo(s2);				
			}
		});
		System.out.println(ls);
		
		//in Java 9 oltre a Arrays.asList( elementi ), si potrebbe usare List.of( elementi )
		
		Collections.sort( ls, //ora usiamo una lambda expression
				(s1, s2) -> {
					if( s1.length()<s2.length() ) return -1;
					if( s1.length()>s2.length() ) return 1;
					return s1.compareTo(s2);					
				}
		);
		
		
/*
		List<Integer> li=new ArrayList<>( Arrays.asList(10, 4, 7, -2, 5, 8, 14, -4) );
		System.out.println(li);
		li.remove( (Integer)(-4) );
		System.out.println(li);
		Collections.sort( li );
		System.out.println(li);
*/		
		
	}
}
