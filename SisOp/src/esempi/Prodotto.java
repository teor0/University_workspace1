package esempi;

import java.util.Arrays;

public class Prodotto {
	public static void main(String[] args) throws InterruptedException{
		int m=2;
		int [] pari=new int[10];
		int [] dispari=new int[10];
		int j=0;
		for(int i=0; i<pari.length; i++) {
			pari[i]=j;
			j+=2;
		}
		j=1;
		for(int i=0; i<dispari.length;i++) {
				dispari[i]=j;
				j+=2;
		}
		if(pari.length!=dispari.length)
			throw new RuntimeException();
		ProdottoScalare[] p=new ProdottoScalare[m];
		int porzione=pari.length/m;
		int ret=0;
		for(int i=0; i<p.length; i++) {
			int inizio=i*porzione;
			int fine=inizio+porzione-1;
			p[i]=new ProdottoScalare(pari,dispari,inizio,fine);
			p[i].start();
			/*try {
				questo blocco renderebbe la soluzione sequenziale e non va bene
				p[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
		
		/*il main ha creato 3 thread che lavorano in parallelo sui dati (4 flussi: Main+3Thread)
		
		for (int i = 0; i < p.length; i++) {
			try {
				//Il main attende p[0], poi p[1], poi p[2]
				p[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		questo blocco se messo al posto di join all'interno della classe prodotto scalare
		fa eseguire il programma alla stessa identica maniera*/
		
		for(int i=0; i<p.length; i++) {
			ret+=p[i].getProdottoScalare();
			System.out.println(ret);
		}
	}
	
}
