package poo.file;
import java.util.*;
import javax.swing.JOptionPane;
import java.io.*;
public class CreaBuffered {
	
	public static File creaBuff() throws IOException{
		String nomeFile=JOptionPane.showInputDialog("Fornisci il nome del file");
		File f= new File(nomeFile);
		DataOutputStream dos= new DataOutputStream( new BufferedOutputStream( new FileOutputStream(f))); 
		while(true) {
			String input=JOptionPane.showInputDialog("Fornire il valore intero di x oppure 0 per terminare");
			int n=Integer.parseInt(input);
			if(n==0)
				break;
			dos.writeInt(n);
		}
		dos.close();
		return f;
	}
	
	public static File creaBuffString() throws IOException{
		String nomeFile=JOptionPane.showInputDialog("Fornisci il nome del file");
		File f= new File(nomeFile);
		FileWriter fr=new FileWriter(f);
		while(true) {
			String input=JOptionPane.showInputDialog("Fornire il valore intero di x oppure 0 per terminare");
			if(input.equals("0"))
				break;
			fr.write(input);
			fr.write("\n");
		}
		fr.close();
		return f;
	}
	
	public static void main(String[] args) throws Exception{
		File f=creaBuffString();
		LettoreDiFile.letturaTxt(f);
		
		/*String nomeFile=JOptionPane.showInputDialog("Fornisci il nome del file");
		DataOutputStream dos= new DataOutputStream( new BufferedOutputStream( new FileOutputStream(nomeFile))); 
		while(true) {
			String input=JOptionPane.showInputDialog("Fornire il valore intero di x oppure 0 per terminare");
			int n=Integer.parseInt(input);
			if(n==0)
				break;
			dos.writeInt(n);
		}
		dos.close();
		DataInputStream dis= new DataInputStream(new BufferedInputStream( new FileInputStream( nomeFile)));
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
	}//main
}
