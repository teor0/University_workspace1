package poo.file;
import java.io.*;
import java.util.*;
public class EsercizioContenuto {
		
	//il contenuto del file 2 deve essere tutto contenuto dentro il file 1
	//non deve(?) essere totalmente uguale, ovvero se vado a capo non devo anche nel file 1
	public static boolean contiene(File f1, File f2) throws Exception{
		Scanner sc1=new Scanner(f1);
		Scanner sc2= new Scanner(f2);
		String l=sc2.nextLine();
		String linea=sc1.nextLine();
		Scanner w1=new Scanner(linea);
		Scanner w2= new Scanner(l);
		boolean flag=false;
		boolean eol=false;
		while(sc1.hasNextLine()) {
			if(eol) {
				linea=sc1.nextLine();
				if(linea.equals(""))
					linea=sc1.nextLine();
				eol=false;
				w1=new Scanner(linea);
			}
			while(w1.hasNext() && w2.hasNext()){
				String s1=w1.next();
				String s2=w2.next();
				if(!s1.equals(s2)) {
					eol=true; 
					w2=new Scanner(l);
					break;
				}
				if(!w1.hasNext())
					eol=true;	
				if(!w2.hasNext() && sc2.hasNextLine()) {
					l=sc2.nextLine();
					if(l.equals("") && sc2.hasNextLine())
						l=sc2.nextLine();
					w2=new Scanner(l);
				}
				if(!w2.hasNext() && !sc2.hasNextLine())
					flag=true;
			}
			if(flag){
				w1.close(); sc1.close();
				w2.close(); sc2.close();
				return true;
			}
		}
		w1.close();
		w2.close();
		sc1.close();
		sc2.close();
		return false;
	}
	
	
	
	public static void main(String [] args) throws Exception {
		//Scanner sc=new Scanner(System.in);
		File f1=new File("C:\\Users\\orlan\\Desktop\\costituzione.txt");
		File f2=new File("C:\\Users\\orlan\\Desktop\\Contenuto.txt");
		System.out.println(contiene(f1,f2));
		//sc.close();
	}
	
	
	
	
	
	
}
