package poo.concorrenza;

//manager dispone di lucchetto
public class Manager implements Risorsa {
	private int c=0; //contatore degli accessi di A
	private boolean risorsaOccupata=false;
	//c e risorsa occupata sono variabili condivise tra i 2 thread
	//quando entro in un metodo synchronized
	//se il lucchetto è aperto lo prendo e lo chiudo
	//se il lucchetto è chiuso aspetto
	/*tutti gli oggetti implicitamente dispongono di
	 * un lucchetto sempre aperto. Quando eseguo un metodo
	 * synchronized chiudo i lucchetti.
	 * ogni oggetto ha anche un wait set
	 */
	public synchronized void richiesta(Processo.Tipo id) {
		//sezione critica- tutto quello che viene scritto nel corpo
		//significa che o lo fa il thread A oppure B
		if(id==Processo.Tipo.A) {
			while(c==2||risorsaOccupata){//si fa un ciclo per ragione strategica
				try {
					wait(); //finisce sul wait set ed apre il lucchetto
				}
				catch(InterruptedException e) {}
			}
			c++;
			risorsaOccupata=true;
			System.out.println("Processo A puo' accedere");
		}
		else {//B fa richiesta
			while(risorsaOccupata) {
				try {
					wait();
				}
				catch(InterruptedException e) {}
			}
			risorsaOccupata=true;
		}
	}//richiesta
	
	public synchronized void rilascio(Processo.Tipo id) {
		if(id==Processo.Tipo.B)
			c=0;
		risorsaOccupata=false;
		notifyAll();
		//"sveglia" i thread nel wait set che hanno eseguito wait
		//svegliato dalla wait l'oggetto si riappropria del lucchetto e dopo averlo ottenuto rifa il while
	}//rilascio
}
