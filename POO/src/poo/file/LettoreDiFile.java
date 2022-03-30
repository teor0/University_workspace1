package poo.file;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Scanner;
public class LettoreDiFile {
	
	public static void letturaInteri(String nomeFile) throws Exception{
		DataInputStream dis= new DataInputStream(new BufferedInputStream( new FileInputStream( nomeFile)));
		System.out.println("Contenuto del file");
		int z;
		for(;;){
			try{
				z=dis.readInt();
			}
			catch( EOFException e ){ 
				break; 
			}
			System.out.println(z);
		}//for
		dis.close();
	}
	
	public static void letturaInteri(File nomeFile) throws Exception{
		DataInputStream dis= new DataInputStream(new BufferedInputStream( new FileInputStream( nomeFile)));
		System.out.println("Contenuto del file");
		int z;
		for(;;){
			try{
				z=dis.readInt();
			}
			catch( EOFException e ){ 
				break; 
			}
			System.out.println(z);
		}//for
		dis.close();
	}
	
	public static void letturaTxt(String nomeFile) throws Exception {
		letturaTxt(new File(nomeFile));
	}
	
	public static void letturaTxt(File nomeFile) throws Exception{
		Scanner sc=new Scanner(nomeFile);
		while(sc.hasNext()) {
			System.out.println(sc.nextLine());
		}
		sc.close();
	}
	
	
	public static void main(String[] args) throws Exception{
		Scanner sc= new Scanner(System.in);
		System.out.println("Fornisci nome file da leggere");
		String nomeFile= sc.nextLine();
		sc.close();

		/*DataInputStream dis= new DataInputStream(new BufferedInputStream( new FileInputStream( nomeFile)));
		System.out.println();
		System.out.println("Contenuto del file");
		int z;
		for(;;){
			try{
				z=dis.readInt();
			}
			catch( EOFException e ){ 
				break; 
			}
			System.out.println(z);
		}//for
		dis.close();*/
	}
}
