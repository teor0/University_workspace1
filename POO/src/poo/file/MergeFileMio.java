package poo.file;
import java.io.*;
import javax.swing.*;
public class MergeFileMio {
	public static void main(String [] args)throws IOException{
		String f1=JOptionPane.showInputDialog("Fornisci il nome del primo file");
		String f2=JOptionPane.showInputDialog("Fornisci il nome del secondo file");
		String f3=JOptionPane.showInputDialog("Fornisci il nome del file di fusione");
		RandomAccessFile raf1= new RandomAccessFile(f1,"r");
		RandomAccessFile raf2= new RandomAccessFile(f2, "r");
		RandomAccessFile raf3= new RandomAccessFile(f3, "rw");
		
		int x1;
		int x2;
		int i=0;
		int j=0;
		while(i<raf1.length()/4 && j<raf2.length()/4) {
			raf1.seek(i*4);
			x1=raf1.readInt();
			raf2.seek(j*4);
			x2=raf2.readInt();
			if(x1<x2) {
				raf3.writeInt(x1);
				i++;
			}
			else {
				raf3.writeInt(x2);
				j++;
			}
		}//while
		
		while(i<raf1.length()/4) {
			raf1.seek(i*4);
			x1=raf1.readInt();
			raf3.writeInt(x1);
			i++;
		}
		while(j<raf2.length()/4) {
			raf2.seek(j*4);
			x2=raf2.readInt();
			raf3.writeInt(x2);
			j++;
		}
		raf1.close();
		raf2.close();
		raf3.close();
		DataInputStream dis= new DataInputStream(new BufferedInputStream( new FileInputStream(f3)));
		System.out.println();
		System.out.println("Contenuto del file");
		int z;
		for(;;){
			try{
				z=dis.readInt();
			}
			catch(EOFException e ){ 
				break; 
			}
			System.out.println(z);
		}//for
		dis.close();
	}
}
