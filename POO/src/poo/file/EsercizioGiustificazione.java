package poo.file;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
public class EsercizioGiustificazione {
	
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
	
	public static int numBuchi(String s) {
		int ret=0;
		char[] linea=s.toCharArray();
		for(int i=0; i<linea.length; i++)
			if(linea[i]==' ')
				ret++;
		return ret;
	}
	
	public static File giustifica(String nomeFile, String fileCopia) throws IOException{
		File f=new File(nomeFile);
		File c=new File(fileCopia);
		Scanner sF=new Scanner(f);
		int max=lineaMax(nomeFile);
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(c)));
		while(sF.hasNextLine()) {
			String linea=sF.nextLine();
			if(linea.length()==max|| linea.charAt(linea.length()-1)=='.')
				pw.write(linea+"\n");
			else {
				int len=linea.length();
				int spaziDaFare=max-len;
				int spaziLiberi=numBuchi(linea);
				double spa=Math.round(spaziDaFare/(double)spaziLiberi);
				String s=sistema(linea, (int)spa, spaziLiberi, max);
				pw.write(s+"\n");
			}
		}
		pw.close();
		sF.close();
		f=new File(c.getAbsolutePath());
		return f;
	}
	
	public static String sistema(String s, int spazi, int buchi, int len) {
		StringBuilder sb=new StringBuilder(len);
		if(buchi==0) {
			String sp=" ";
			sp=sp.repeat(spazi+1);
			sb.append(sp);
			sb.append(s);
		}
		else {
			Scanner sc=new Scanner(s);
			while(sc.hasNext() && !(sb.length()==len)){
				sb.append(sc.next());
				if(!(sb.length()==len)) {
					if(spazi%2!=0) {
						sb.append(" ".repeat(spazi+1));
						spazi--;
					}
					else
						sb.append(" ".repeat(spazi+1));
				}		
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Fornisci il file");
		//String nomeFile=br.readLine();
		String nomeFile="C:\\Users\\orlan\\Desktop\\test.txt";
		String copiaFile="C:\\Users\\orlan\\Desktop\\tes.txt";
		File f=new File(nomeFile);
		Scanner sc=new Scanner(f);
		while(sc.hasNextLine()) {
			System.out.println(sc.nextLine());
		}
		sc.close();
		f=giustifica(nomeFile, copiaFile);
		Scanner scan=new Scanner(f);
		while(scan.hasNextLine()) {
			System.out.println(scan.nextLine());
		}
		scan.close();
	}
	
	
}
