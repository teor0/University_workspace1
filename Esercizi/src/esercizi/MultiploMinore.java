package esercizi;

public class MultiploMinore {

	public static int minMul() {
		int min=Integer.MAX_VALUE;
		for(int c=Integer.MAX_VALUE; c>50; c--)
			if(divisibile(c) && c<min)
				min=c;
		return min;
	}
	
	public static boolean divisibile(int n) {
		for(int i=2; i<=20; i++)
			if(!(n%i==0))
				return false;
		return true;
	}
	
}
