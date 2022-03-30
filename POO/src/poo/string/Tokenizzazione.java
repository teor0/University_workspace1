package poo.string;

import java.util.*;

public class Tokenizzazione {
	public static void main( String[] args ) {
		Scanner sc=new Scanner( System.in );
		System.out.print("Fornisci una linea : ");
		String linea=sc.nextLine();
		/*1*/
		System.out.println("Tokenizzazione mediante StringTokenizer");
		StringTokenizer st=new StringTokenizer(linea," ", true); //non ammessa regex
		int tok=0;
		while( st.hasMoreTokens() ) {
			String token=st.nextToken();
			//elabora token
			System.out.println(token);
		}
		/*2*/
		System.out.println("Tokenizzazione mediante Scanner");
		Scanner ss=new Scanner( linea );
		ss.useDelimiter("\\W+"); //usa una regex
		while( ss.hasNext() ) {
			String token=ss.next();
			
			//elabora token
			System.out.println(token);
		}
		/*3*/
       	 	System.out.println("Tokenizzazione mediante split");
        	String token[] = linea.split("\\S+");
        	for( int i=0; i<token.length; ++i ) {
        		System.out.println(token[i]);
        		tok++;
        	}
		sc.close();
		System.out.println(tok);
	}//main
}
