package poo.util;

import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileReader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.*;
import poo.polinomi.*;
public final class PoliUtil {
	private PoliUtil() {}
	
	public static Polinomio validazione(String s) {
		//?=significa che quello scritto dopo può mancare
		String regeM="((\\-?\\d+(?=[xX]))?(\\-?[xX])(\\^(\\d+))?)|((\\-?)[xX])|(\\-?\\d+)";
		String regeP="[(((\\-\\+)?\\d+(?=[xX]))?(\\-?[xX])(\\^(\\d+))?)|((\\-?)[xX])|(\\-?\\d+)]+";
		Polinomio p=new PolinomioLL();
		Pattern paP=Pattern.compile(regeP);
		Matcher maP=paP.matcher(s);
		Pattern paM=Pattern.compile(regeM);
		Matcher maM=paM.matcher(s);
		if(!maP.matches())
			throw new IllegalArgumentException("Polinomio impossibile");
		while(maM.find()) {
			int coef=0;
			int grado=0;
			if(maM.group(3)!=null && maM.group(2)!=null) { //ho scritto tipo 12x^2
				if(maM.group(5)!=null)//prendo il grado se c'è
					grado=Integer.parseInt(maM.group(5));
				else 
					grado=1;
				coef=Integer.parseInt(maM.group(2));
			}
			else {
				if(maM.group(2)==null && maM.group(5)==null && maM.group(3)!=null){// ho scritto solo x
					coef=1;
					grado=1;
				}
				else if(maM.group(2)==null && maM.group(5)!=null && maM.group(3)!=null){//grado diverso da 1
					coef=1;
					grado=Integer.parseInt(maM.group(5));
				}
				else {
					coef=Integer.parseInt(maM.group());//coef diverso da 1
				}
			}
			p.add(new Monomio(coef,grado));
		}
		return p;
	}
	
	public static Collection<Polinomio> ripristina(File nomeFile) throws IOException {
		BufferedReader br= new BufferedReader(new FileReader(nomeFile));
		LinkedList<Polinomio> c= new LinkedList<Polinomio>() ;
		for(;;) {
			String poli=br.readLine();
			if(poli==null) {
				break;
			}
			Polinomio po=validazione(poli);
			c.add(po);
		}
		br.close();
		return c;
	}
	
	public static void salva(String nomeFile, Collection<Polinomio> lista) throws IOException{
		PrintWriter pw= new PrintWriter(new FileWriter(nomeFile));
		for(Polinomio p:lista)
			pw.println(p.toString());
		pw.close();
	}
	
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		String poli=sc.nextLine();	
		validazione(poli);
		sc.close();
		}
}
