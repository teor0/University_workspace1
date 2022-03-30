package aziendaAgricola;

import java.util.LinkedList;

public abstract class Azienda {

	protected int nSacchi;
	protected int costo=3;
	protected int incasso;
	protected int sacchettiIniziali;
	
	public Azienda(int nSacchi) {
		this.nSacchi=nSacchi;
		incasso=0;
		
	}
	
	
	public abstract void ritira();
	public abstract void caricaSacchi();
	public abstract void paga(int nSacchi);
	
	public int getIncasso() {
		return incasso;
	}
	
	public void test(int n) {
		Magazziniere m=new Magazziniere(this);
		Cliente[] clienti=new Cliente[n];
		for(int i=0; i<clienti.length; i++) 
			clienti[i]=new Cliente(i,this);
		m.start();
		for(int j=0; j<clienti.length; j++)
			clienti[j].start();	
	}
	
}
