package roulette;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.io.*;
public class Roulette {

	private TreeMap<Integer,Integer>[]  numeri=new TreeMap[37];
	private TreeSet<Integer> estratti=new TreeSet<>();
	
	public Roulette(File f) throws IOException{
		if(!f.exists())
			throw new RuntimeException();
		String regex="([0-9]|[1-2][0-9]|3[0-6])(\\s([0-9]|[1-2][0-9]|3[0-6]))*";
		Scanner sc=new Scanner(f);
		int x=37;
		while(sc.hasNextLine()) {
			String linea=sc.nextLine();
			if(!linea.matches(regex))
				throw new RuntimeException();
			Scanner num=new Scanner(linea);
			while(num.hasNextInt()) {
				int n=num.nextInt();
				estratti.add(n);
				if(x!=37) {
					if(numeri[x]==null)
						numeri[x]=new TreeMap<>();
					if(!numeri[x].containsKey(n))
						numeri[x].put(n,0);
					numeri[x].put(n, numeri[x].get(n)+1);
				}
				x=n;	
			}
		}
		sc.close();
	}
	
	public boolean uscito(int x) {
		return estratti.contains(x);
	}
	
	public int numeroUscitoPiuDiFrequenteDopo(int x) {
		if(!uscito(x))
			throw new IllegalArgumentException();
		int max=0;
		int npf=-1;
		for(int c:numeri[x].keySet())
			if(numeri[x].get(c)>max) {
				max=numeri[x].get(c);
				npf=c;
			}
		return npf;
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder(100);
		for(int n:estratti) {
			if(numeri[n]!=null)
				for(int x :numeri[n].keySet()) 
					sb.append(n+ " seguito da "+ x+ " "+ numeri[n].get(x)+ " numero di volte"+"\n");
			else
				continue;
		}
		return sb.toString();
	}
	
	public static void main(String [] args) throws IOException {
		File f=new File("C:\\Users\\orlan\\Desktop\\test.txt");
		Roulette r=new Roulette(f);
		System.out.println(r.toString());
		int t=36;
		System.out.println(r.numeroUscitoPiuDiFrequenteDopo(t));
		System.out.println(r.uscito(t));
	}
	
}
