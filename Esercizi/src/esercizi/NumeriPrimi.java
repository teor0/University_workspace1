package esercizi;

import java.util.LinkedList;

public class NumeriPrimi {
	public static boolean ePrimo(int n){
	        if (n <= 1)
	            return false;
	        else if (n == 2)
	            return true;
	        else if (n % 2 == 0)
	            return false;
	        for (int i = 3; i <= Math.sqrt(n); i += 2)
	            if (n % i == 0)
	                return false;
	        return true;
	}
	
	public static int divPrimoMax(int n) {
		int max=1;
		for(int i=1; i<n; i++)
			if(ePrimo(i) && n%i==0)
				if(i>max)
					max=i;
		return max;
	}
	
	public static long sumTill(int n) {
		long ret=0;
		for(int i=0; i<n; i++)
			if(ePrimo(i))
				ret+=i;
		return ret;
	}
	
	public static int generatorePrimi(LinkedList<Integer> l) {
		int m=2147483647; 
		for(int i=0; i<m; i++) {
			if(ePrimo(i))
				l.add(i);
			if(l.size()==10001)
				break;
		}
		return l.getLast();
	}
	
	public static long maxPrimeFactors(long n) 
    { 
        long maxPrime = -1; 
        while (n % 2 == 0) { 
            maxPrime = 2; 
            n >>= 1; 
        } 
        for (int i = 3; i <= Math.sqrt(n); i += 2) { 
            while (n % i == 0) { 
                maxPrime = i; 
                n = n / i; 
            } 
        }   
        if (n > 2) 
            maxPrime = n;   
        return maxPrime; 
    } 
  
}
