package poo.file;

import java.io.BufferedReader;
import java.io.*;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ScritturaSuFile {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Fornisci il file");
		String nomeFile=br.readLine();
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(nomeFile)));
		for(;;) {
			System.out.println("Invio per terminare");
			String linea=br.readLine();
			if(linea=="")
				break;
			pw.print(linea+"\n");
		}
		pw.close();
		br.close();
		System.out.println("Applicazione terminata");
	}
	
	
	
}
