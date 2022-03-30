package poo.esempi;

import java.util.Scanner;
import java.util.StringTokenizer;

import poo.util.*;
//simulazione di una coda ad una cassa
public class TestCoda {
	public static void main( String[] args ) {
		Scanner sc=new Scanner( System.in );
		System.out.println("Fornisci una stringa");
		String COMANDO="([Aa]\\s+\\w+|[Pp]|[Qq])";
		Queue<String> coda=new BoundedBuffer<>(5);
		loop: for(;;) {
			System.out.print("> ");
			String linea=sc.nextLine();
			if( !linea.matches(COMANDO) )
				System.out.println("Cosa?");
			else {
				char c=Character.toUpperCase( linea.charAt(0) );
				switch( c ) {
				case 'Q': System.out.println("Rimangono in coda: "+coda); break loop;
				case 'A':
					StringTokenizer st=new StringTokenizer( linea, " ");
					st.nextToken(); 
					String nickName=st.nextToken();
					try {
						coda.add( nickName ); 
						System.out.println("Sono in coda: "+coda);
					}catch( RuntimeException e ) {
						System.out.println("Coda e' piena!!!");
					}
					break;
				default : 
					try {
						System.out.println("Va via "+coda.remove()+ " rimangono: "+coda);
					}catch( RuntimeException e ) {
						System.out.println("Coda vuota!!!");
					}
				}//switch
			}//if
		}
		sc.close();
		System.out.println("Bye.");
	}

}//TestCoda
