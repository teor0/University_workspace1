package poo.concorrenza;

public class Main {
	public static  void main( String [] args ) {
		//N.B. l'applicazione può essere infinita
		Risorsa m=new Manager();
		Processo a=new Processo( Processo.Tipo.A, m );
		Processo b=new Processo( Processo.Tipo.B, m );
		a.start(); b.start();
	}
}
