package poo.riferimentiIncrociati;

import java.io.*;
import java.util.*;

public class RiferimentiIncrociati {
	
	 static TreeMap<String,TreeSet<Integer>> riferimenti=new TreeMap<>(
			(String s1,String s2)->{
				if(s1.length()<s2.length())
					return -1;
				if(s1.length()> s2.length())
					return 1;
				return s1.compareTo(s2);
	});
	
	public static boolean verificaFile(File f) throws IOException{
		String regex="\\w+:\\d+";
		Scanner sc=new Scanner(f);
		while(sc.hasNextLine()) {
			String linea=sc.nextLine();
			if(!linea.matches(regex))
				throw new RuntimeException("File non corretto");
		}
		sc.close();
		return true;
	}
	
	public static void creaIndice(File f) throws IOException {
		verificaFile(f);
		Scanner sc=new Scanner(f);
		while(sc.hasNextLine()) {
			String linea=sc.nextLine();
			String[] token= linea.split(":");
			if(!riferimenti.containsKey(token[0])) {
				riferimenti.put(token[0], new TreeSet<Integer>());
			}
			riferimenti.get(token[0]).add(Integer.parseInt(token[1]));
		}
		sc.close();
	}
	
	public static void scriviSuFile(String nomeFile) throws IOException {
		Set<String> parole=riferimenti.keySet();
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(nomeFile)));
		for(String parola:parole) {
			pw.write(parola+"\n");
			pw.write(Arrays.toString(riferimenti.get(parola).toArray())+"\n");
		}
		pw.close();
	}
	
	public static void visualizzaIndice() {
		for(String p: riferimenti.keySet()) {
			System.out.println(p);
			for(int n:riferimenti.get(p))
				System.out.print(n+" ");
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("Fornisci il file di testo");
		File f=new File(sc.nextLine());
		creaIndice(f);
		visualizzaIndice();
		System.out.println("Fornisci il file su cui salvare");
		scriviSuFile(sc.nextLine());
		System.out.println("Soluzione correttemente creata");
	}
	
	
}
