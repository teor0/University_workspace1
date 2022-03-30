package esempi;
import java.io.*;
public class Seminario extends Thread{
	
	private int da,a;
	static byte[] inputBytes;
	static String soluzione="sisop";
	
	public Seminario(int da, int a, String nomeFile) throws IOException{
		this.da=da;
		this.a=a;
		File f= new File(nomeFile);
		FileInputStream inS= new FileInputStream(f);
		byte[] inB= new byte[(int) f.length()];
		inS.read(inB);
		inputBytes=inB;
		inS.close();
	}
	//0000001123581321
	public void run() {
		for(int i=da; i<=a; i++) {
			String key=String.format("%016d", i);
			try {
				String risultato=new String(MyCrypto.decrypt(key, inputBytes));
				if(risultato.toLowerCase().indexOf(soluzione)!=-1) {
					System.out.println("La chiave: "+key);
					System.out.println(risultato);
					System.exit(0);
				}
			}catch(Exception e) {
			}
		}
		System.out.println(this.getName()+ "ha finito");
	}//run
	
	public static void main(String[] args) throws IOException {
		String nomeFile="C:\\Users\\orlan\\Desktop\\document2021.encryptedOK";
		Seminario[] s=new Seminario[8];
		int passo=Integer.MAX_VALUE/s.length;
		int ret=Integer.MAX_VALUE%s.length;
		for(int i=0;i< s.length; i++) {
			s[i]=new Seminario(i*passo, (i*passo)+passo, nomeFile);
			s[i].start();
		}
		if(ret!=0) {
			Seminario semR= new Seminario(s.length*passo, (s.length*passo+ret), nomeFile);
			semR.start();
		}
	}
	
}
