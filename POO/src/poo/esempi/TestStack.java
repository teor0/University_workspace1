package poo.esempi;
import java.util.*;
import poo.util.Stack;
import poo.util.StackConcatenato;
public class TestStack {
	public static void main(String[] args) {//verifica stringa palindroma
		String INPUT="\\w+\\$\\w+";
		Scanner sc= new Scanner(System.in);
		System.out.println("Fornisci una linea");
		String linea= sc.nextLine().toLowerCase(); //per esempio casa$asac
		if(!linea.matches(INPUT))
			throw new RuntimeException("Linea errata");
		Stack<Character> pila=new StackConcatenato<>();
		int i=linea.indexOf('$');
		for(int j=0; j<i; j++)
			pila.push(linea.charAt(j));
		int k=i+1;
		for(; k<linea.length(); k++) {
			try {
				if(pila.pop()!=linea.charAt(k))
					break;
			}
			catch(RuntimeException e) {
				System.out.println("Linea non palindroma");
				System.exit(-1);
			}
		}
		if(k<linea.length() || !pila.isEmpty())
			System.out.println("Linea non palindroma");
		else
			System.out.println("Linea palindroma");
		sc.close();
	}//main
}
