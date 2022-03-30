package poo.file;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

import java.nio.file.*;
public class EsercizioFile {
	//esercizio su file di tipo testo
	
	public static int lineaMax(String nomeFile) throws IOException{
		Scanner sF= new Scanner(new File(nomeFile));
		int max=0;
		while(sF.hasNextLine()){
			String linea=sF.nextLine();
			if(linea.length()>max)
				max=linea.length();
		}
		return max;
	}
	
	public static void main(String[] args)throws IOException{
		System.out.println("Fornisci il nome del file: ");
		//Scanner input= new Scanner(System.in);
		//String path= input.nextLine();
		//input.close();
		String path="C:\\Users\\orlan\\Desktop\\varie.txt";
		System.out.println(EsercizioFile.lineaMax(path));
		int nWord=0;
		int nChar=0;
		int nLinee=0;
		ArrayList<Integer> lunghezzaLin=new ArrayList<>();
		Scanner sc= new Scanner(new File(path));
		try {
			while(sc.hasNextLine()) {
				String linea=sc.nextLine();
				Scanner scword= new Scanner(linea);
				while(scword.hasNext()) {
					String w=scword.next();
					nWord++;
					for(char c:w.toCharArray())
						nChar++;
				}
				scword.close();
				nLinee++;
				lunghezzaLin.add(linea.length());
			}
			sc.close();
		}finally {
		}
		System.out.println("Numero di parole alfanumeriche: " + nWord);
		System.out.println("Numero di caratteri: " +nChar);
		System.out.println("Numero di linee: " +nLinee);
		for(int i=0; i<lunghezzaLin.size(); i++) {
			System.out.println("La lunghezza della linea " +(i+1)+" e:"+lunghezzaLin.get(i));
		}
	}
}
