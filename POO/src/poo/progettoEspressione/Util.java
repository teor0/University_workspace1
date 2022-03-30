package poo.progettoEspressione;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;
import poo.util.StackConcatenato;

//Svolto da Matteo Orlando matricola 213430
public final class Util {
	
	private Util() {}
	
	private static boolean valutazione(String s) {
		String exp="(\\d+|[\\+\\-\\*/\\(\\)\\^\\%])+"; 
		if(!s.matches(exp))
			return false;
		return true;
	}
	
	public static int valutaEspressione(String esp) {
		String ope="\\+\\-\\*/\\(\\)\\^\\%";
		if(!valutazione(esp))
			throw new ArithmeticException("Espressione malformata");
		StringTokenizer st=new StringTokenizer(esp,ope,true);
		StackConcatenato<Integer> operandi=new StackConcatenato<Integer>();
		StackConcatenato<String> operatori=new StackConcatenato<String>();
		return valutaEspressione(esp, st, operandi,operatori);
	}
	
	private static int valutaEspressione(String espr, StringTokenizer st, StackConcatenato<Integer> operandi, StackConcatenato<String> operatori){
		String num="\\d+";
		if(st.hasMoreTokens()) {
			String s=st.nextToken();
			if(s.equals("(")) //invoco ricorsivamente il metodo
				operandi.push(valutaEspressione(espr,st,new StackConcatenato<Integer>(),new StackConcatenato<String>()));
			else if(s.equals(")")) { //calcolo il risultato dentro le parentesi
				 operandi.push(risultato(operandi.pop(),operandi.pop(),operatori.pop()));
				 return operandi.pop();
			}
			else if(s.matches(num)) {
				operandi.push(Integer.parseInt(s));
			}
			else{//ho un operatore quindi mi comporto come descritto nella traccia
				if(operatori.isEmpty() || prioritario(s,operatori.peek()))
					operatori.push(s);
				else {
					operandi.push(risultato(operandi.pop(), operandi.pop(),operatori.pop()));
					operatori.push(s);
				}
			}
			return valutaEspressione(espr,st, operandi,operatori);
		}
		else {
			operandi.push(risultato(operandi.pop(), operandi.pop(),operatori.pop()));
		}// sono arrivato alla fine percio ritorno il risultato
		return operandi.pop();
	}
	
	private static int risultato(int o1, int o2, String op){
		int ret=0;
		switch(op) {
		case "+":
			ret=o2+o1;
			break;
		case "-":
			ret=o2-o1;
			break;
		case "*":
			ret=o2*o1;
			break;
		case "/":
			ret=o2/o1;
			break;
		case "^":
			ret=(int) Math.pow((double)o2, (double)o1);
			break;
		case "%":
			ret=o2%o1;
			break;
		}
		return ret;
	}
	
	
	//NB PROVARE A FARE UN COMPARATORE COSI CHE SIA CONSISTENTE
	private static boolean prioritario(String operatore, String cima) {
		int prioO=0;
		int prioC=0;
		HashSet<String> s3=new HashSet<>();
		s3.add("^");
		HashSet<String> s2=new HashSet<>();
		s2.add("*"); 
		s2.add("/");
		s2.add("%");
		HashSet<String> s1=new HashSet<>();
		s1.add("+");
		s1.add("-");
		if(s3.contains(operatore))
			prioO=3;
		if(s3.contains(cima))
			prioC=3;
		if(s2.contains(operatore))
			prioO=2;
		if(s2.contains(cima))
			prioC=2;
		if(s1.contains(operatore))
			prioO=1;
		if(s1.contains(cima))
			prioC=1;
		if(prioO>prioC)
			return true;
		return false;
	}
}
