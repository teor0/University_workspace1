package poo.regex;

import java.util.Scanner;

public class RegexEsame {
	
	public static void main(String[] args) {
		String reale="\\-?(\\d+|(\\d+)?\\.\\d+)([Ee][\\-\\+]?\\d{1,3})?[DdFf]?";
		String targa="[A-Z]{2}[0-9]{3}[A-Z]{2}";
		String rpn="(\\d+)(\\s+\\d+(\\s+(\\d+|[\\+\\-\\*/]))+)?";
		Scanner sc=new Scanner(System.in);
		String s= sc.nextLine();
		sc.close();
		if(!s.matches(reale))
			System.out.println("Non e' un reale");
		else
			System.out.println("Numero reale");
		if(!s.matches(targa))
			System.out.println("non e' una targa");
		else
			System.out.println("Targa valida");
	}
	
	
	

}
