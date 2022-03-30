package esercizi;

public class PalindromoMaggiore {
	
	public static boolean palindromo(int x) {
		String s=""+x;
		int j=s.length()-1;
		for(int i=0; i<=s.length()/2; i++) {
			char c=s.charAt(i);
			char c2=s.charAt(j);
			if(c==c2)
				j--;
			else {
				return false;
			}
		}
		return true;
	}
	
	public static int max(int num) {
		/*String s="1";
		if(num==0)
			return -1;
		String z="0";
		z=z.repeat(num);
		s+=z;
		int k=Integer.parseInt(s);
		String nov="9";
		nov=nov.repeat(num);
		int l=Integer.parseInt(nov);*/
		int max=0;
		for(int i=10; i<=99; i++)
			if(palMax(i)>max)
				max=palMax(i);
		return max;
	}
	
	public static int palMax(int x) {
		int max=0;
		for(int i=10; i<=99; i++) {
			int prod=x*i;
			if(prod>max && palindromo(prod))
				max=prod;
		}
		return max;
	}
}
