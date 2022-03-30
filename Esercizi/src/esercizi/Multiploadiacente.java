package esercizi;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Multiploadiacente {

	public static long maxMul(String s){
		long max=0;
		ArrayList<Long> hs=new ArrayList<>();
		for(int i=0; i<=s.length()-13; i++) {
			String k=s.substring(i, i+13);
			long mul=1L;
			for(char c:k.toCharArray()){
				long n=Character.getNumericValue(c);
				mul=mul*n;
			}
			hs.add(mul);
			if(mul>max)
				max=mul;
		}
		System.out.print(hs);
		return max;
	}
	
	
	
}
