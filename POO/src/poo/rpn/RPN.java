package poo.rpn;
import java.io.*;
import poo.util.Stack;
import poo.util.StackConcatenato;

import java.util.*;
public class RPN {
	
	private HashMap<String,Integer> m=new HashMap<>(); //coppie dentro memoria
	
	public RPN(String nomeFile) throws IOException {
		File f=new File(nomeFile);
		if(!f.exists())
			throw new IllegalArgumentException("file non esiste");
		String regex="[a-zA-Z_]\\w*\\s+\\d+"; //regex che rappresenta: alfa 41
		Scanner sc=new Scanner(f);
		while(sc.hasNextLine()) {
			String linea=sc.nextLine();
			if(!linea.matches(regex))
				throw new IllegalArgumentException("memoria errata");
			String[] token=linea.split("\\s+");
			m.put(token[0], Integer.parseInt(token[1]));
		}
		sc.close();		
	}
	
	
	public int valuta(String espr) {
		String var="[a-zA-Z]\\w*";
		String num="\\d+";
		String operando="("+var+"|"+num+")";
		String expr=operando+"(\\s+"+operando+"(\\s+("+operando+"|[\\+\\-\\*/]))+)?";
		if(!espr.matches(expr))
			throw new IllegalArgumentException("espressione malformata");
		StringTokenizer st=new StringTokenizer(espr," ");
		Stack<Integer> stack=new StackConcatenato<>();
		int val=0; int o2=0; int o1=0;
		while(st.hasMoreTokens()) {
			String tk=st.nextToken();
			if(tk.matches(operando)) {
				try {
					if(tk.matches(num))
						val=Integer.parseInt(tk);
					else 
						val=m.get(tk);
					stack.push(val);
				}catch(RuntimeException e) {
					throw new IllegalArgumentException("espressione malformata");
				}
			}//if
			else {//operatore
				try {
					o2=stack.pop();
					o1=stack.pop();
					switch(tk.charAt(0)) {
					case '+':
						stack.push(o1+o2);
						break;
					case '-':
						stack.push(o1-o2);
						break;
					case '*':
						stack.push(o1*o2);
						break;
					default:
						stack.push(o1/o2);
					}
				}catch(RuntimeException e) {
					throw new IllegalArgumentException("espressione malformata");
				}
			}//else
		}//while
		if(stack.size()!=1)
			throw new RuntimeException("espressione malformata");
		return stack.pop();
	}
	

	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("Fornisci nome file");
		String nome=sc.nextLine();
		System.out.println("Fornisci espressione");
		String espressione=sc.nextLine();
		RPN r=new RPN(nome);
		int ret=r.valuta(espressione);
		System.out.println(ret);
		sc.close();
	}

}
