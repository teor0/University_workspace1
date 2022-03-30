package esercizi;
import java.util.Arrays;
public class Algo {
	public static void main(String[] args){
		int [] v={5,6,1,4,2,7,3,9,8,0};
		System.out.println(Arrays.toString(v));
		selectionSort(v);
		System.out.println(Arrays.toString(v));
	}
	
	static int ricercaLineare(int [] a, int x){
		int i=0;
		while(i<a.length){
			if(a[i]==x)
				return i;
			i++;
		}
		return -1;
	}
	
	static int ricercaBinaria(int[] a, int x , int inf, int sup){
		int med=(inf+sup)/2;
		if(inf>sup || sup<inf)
			return -1;
		if(a[med]==x)
			return med;
		else if(a[med]<x)
			return ricercaBinaria(a,x,med+1,sup);
		return ricercaBinaria(a,x,inf,med-1);
	}
	
	static void selectionSort(int[] v){
		int sortPos=0;
		while(sortPos<v.length){
			int min=v[sortPos];
			int iMin=sortPos;
			for(int i=sortPos+1; i<v.length; i++){
				if(v[i]<v[iMin])
					iMin=i;
			}
			int tmp=v[sortPos];
			v[sortPos]=v[iMin];
			v[iMin]=tmp;
			sortPos++;
		}
	}
}
