package esercizi;

public class Permutazioni {

	public static void permuta(int[] a) {
		permutazioni(a,0);
	}
	
	private static void permutazioni(int[] a, int i) {
		if(i==a.length-1)
			System.out.println(java.util.Arrays.toString(a));
		else 
			for(int j=i; j<a.length; j++) {
				int tmp=a[i];
				a[i]=a[j];
				a[j]=tmp;
				permutazioni(a,i+1);
				int tp=a[j];
				a[j]=a[i];
				a[i]=tp;
			}
	}
	
	public static void main(String[] args) {
		int[] a= {1,2,3,4,5};
		permuta(a);
	}
	
	
}
