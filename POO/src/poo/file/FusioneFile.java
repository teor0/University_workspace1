package poo.file;
import java.io.*;
import java.util.*;
public class FusioneFile {
	
	
	
	public static void fusione(File f1, File f2, String nuovoFile) throws IOException{
		File ret= new File(nuovoFile);
		Scanner sc= new Scanner(f1);
		DataInputStream dis= new DataInputStream(new FileInputStream(f2));
		DataOutputStream dos=new DataOutputStream(new FileOutputStream(ret));
		while(sc.hasNext()) {
			try {
				int y=dis.readInt();
				String s=sc.nextLine();
				String n=s+y;
				dos.writeInt(Integer.parseInt(n));
			}
			catch(Exception e) {
				break;
			}
		}
		
		if(sc.hasNext())
			while(sc.hasNext()) {
				int b=Integer.parseInt(sc.nextLine());
				dos.writeInt(b);
			}
		for(;;) {
			try {
				int y=dis.readInt();
				dos.writeInt(y);
			}
			catch(Exception e) {
				break;
			}
		}
		dis.close();
		dos.close();
	}
	
	
	
	
	public static void main(String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		System.out.println("Fornisci file 1 testuale");
		String s1=sc.nextLine();
		File f1=new File(s1);
		System.out.println("Fornisci file 2 di interi");
		String s2=sc.nextLine();
		File f2=new File(s2);
		System.out.println("Fornisci file fusione");
		String s3=sc.nextLine();
		fusione(f1,f2,s3);
		LettoreDiFile.letturaInteri("C:\\Users\\orlan\\Desktop\\Lallo.dat");
	}

}
