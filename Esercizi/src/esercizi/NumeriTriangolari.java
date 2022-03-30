package esercizi;
import java.util.ArrayList;
public class NumeriTriangolari {
	
	public static long calcolo(long n) {
		long ret=0;
		long k=n+1;
		ret=(k*n)/2;
		return ret;
	}
	
	public static int divisori(long n) {
		ArrayList<Integer> al=new ArrayList<>();
		al.add(1);
		al.add(0);
		for(int i=2; i<=Math.sqrt(n); i++) {
			if(al.size()>500)
				break;
			if(n%i==0)
				if(n/i==i)
					al.add(i);
				else {
					al.add(i);
					al.add((int) (n/i));
				}
		}
		return al.size();
	}
	
	public static long maggiorDiv(int limite) {
		boolean flag=false;
		long ret=0;
		long n=12374;
		int div=0;
		while(!flag) {
			long c=calcolo(n);
			div=divisori(c);
			if(div>limite) {
				ret=c;
				break;
			}
			n++;
		}
		System.out.println(n);
		System.out.println(div);
		return ret;
	}
	
}
