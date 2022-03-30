package esercizi;

public class DifferenzaQuadrati {
		
	public static int sommaFinoN(int n) {
		int ret=0;
		int k=n+1;
		ret=(k*n)/2;
		return ret;
	}
	
	public static int diffQuad(int k) {
		int ret=0;
		int quadsum=(int) Math.pow(sommaFinoN(k),2);
		int c=0;
		for(int i=1; i<=k; i++) {
			c+=(int) Math.pow(i,2);
		}
		ret=quadsum-c;
		return ret;
	}
	
	
	
	
}
