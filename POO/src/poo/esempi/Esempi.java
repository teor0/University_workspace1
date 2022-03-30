package poo.esempi;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
import poo.util.Array;
import poo.figure.*;
import poo.geometria.*;
import poo.razionali.*;
import poo.giochi.*;
import poo.date.*;
import poo.contatori.*;
public class Esempi {
	
	public static void main(String[] args) {
		/*Integer[] p= {10,5,6,78,7,8,1,7,50};
		System.out.println(Arrays.toString(p));
		Array.selectionSort(p);
		System.out.println(Arrays.toString(p));*/
		
		/*double [][] a= {
				{7,8},
				{3,5,}
		};
		double [] y= {10,4};
		double [] k= new double[2]; 
		System.arraycopy(y, 0, k, 0, y.length);
		System.out.println(Arrays.toString(k));
		System.out.println(Matrix.stampa(a));
		System.out.println(" ");
		System.out.println(Matrix.stampa(a));
		*/
		String mono="((\\d+[xX])|(\\d+[xX]\\^\\d+)|\\d))";
		Integer[] a= {90,1,73,69,3,6,18,7,2};
		LinkedList<Integer> l=new LinkedList<Integer>(java.util.Arrays.asList(a));
		Iterator<Integer> it=l.iterator();
		
		System.out.println();
	}

}
