package poo.regex;
import java.util.*;

public class Classificatore {
	public static void main(String []args){
		System.out.println("Fornisci, uno per linea, un numero o un identificatore e termina con STOP");
		Scanner sc=new Scanner(System.in);

		String INTERO="\\-?\\d+";
		String REALE="\\-?(\\d+|(\\d+)?\\.\\d+)([eE][\\-\\+]?\\d{1,3})?[DdFf]?";
		String IDENTIFICATORE="[a-zA-Z_][\\w\\$]*"; //[a-zA-Z0-9_\\$]*";
		
		String s=null;
		for(;;){
			System.out.print(">>");
			s=sc.nextLine();
			if( s.equalsIgnoreCase("STOP") ) 
				break;
			if( s.matches(INTERO) ){
				int x=Integer.parseInt(s);
				System.out.println("int>>"+x);
			}
			else if( s.matches(REALE) ){
				double x=Double.parseDouble(s);
				System.out.printf("double>>%1.2f",x);
				System.out.println(" originale: "+s);
			}
			else if( s.matches( IDENTIFICATORE ) ){
				System.out.println("Identificatore Java legale: "+s);
			}
			else
				System.out.println("Simbolo "+s+" non riconosciuto");
		}
		System.out.println("Bye!");
		sc.close();
	}
}//Classificatore
 