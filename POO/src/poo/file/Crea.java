package poo.file;
import java.io.*;
import java.util.*;
/**
 * 
 * Crea un file di interi a partire da una successione
 * letta da tastiera, e terminante col primo 0.
 *
 */
public class Crea{
	public static void main( String []args )throws IOException {
		Scanner s=new Scanner( System.in );
		System.out.print("nome file da creare=");
		String nome=s.nextLine();
		DataOutputStream dos=new DataOutputStream( new FileOutputStream(nome) );
		System.out.println("Fornisci una serie di interi uno per linea. Solo 0 termina");
		int x=0;
		for(;;){
			System.out.print("int>");
			int input=s.nextInt();
			if( input==0 ) 
				break;
			dos.writeInt(input);
		}
		dos.close();
		DataInputStream dis=new DataInputStream( new FileInputStream(nome) );
		System.out.println();
		System.out.println("Contenuto del file");
		for(;;){
			try{
				x=dis.readInt();
			}
			catch( EOFException e ){ 
				break; 
			}
			System.out.println( x );
		}//for
		dis.close();
	}//main
}//Crea