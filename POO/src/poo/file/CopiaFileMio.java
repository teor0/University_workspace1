package poo.file;

import java.io.*;
import java.util.Scanner;

public class CopiaFileMio {
	public static void main( String []args ) throws IOException {
		File f=new File("C:\\Users\\orlan\\Desktop\\test.txt");
		File c=new File("C:\\Users\\orlan\\Desktop\\tes.txt");
		f.delete();
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(f)));
		Scanner sc=new Scanner(c);
		while(sc.hasNextLine()) {
			pw.write(sc.nextLine()+"\n");
		}
		sc.close();
		pw.close();
		System.out.println("File correttamente copiato");
	}//main
}
