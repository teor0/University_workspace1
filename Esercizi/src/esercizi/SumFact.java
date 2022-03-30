package esercizi;

public class SumFact {
	
	public static long fact(long n) {
		if(n<=1)
			return 1;
		return n*fact(n-1);
	}
	
	public static long sum(String s) {
		long ret=0;
		for(char c:s.toCharArray())
			ret= ret+Character.getNumericValue(c);
		return ret;
	}
	
	
}
