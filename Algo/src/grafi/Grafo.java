package grafi;

import java.util.*;

/**
 * 
 */

/**
 * @author sflesca
 * 
 */
public abstract class Grafo<A extends Arco> {

	protected int n;
	protected int m;

	/**
	 * 
	 */
	public Grafo(int n) {
		this.n = n;
		this.m = 0;
	}
	
	public int n(){
		return n;
	}
	
	public int m(){
		return m;
	}
	
	public abstract Iterator<A> archi();//MATRICE: theta(n^2) 
										//LISTA: theta(n+m) //si conta assieme alla sommatoria il fatto di scansionare 
										// i nodi nel caso in cui gli archi non ce ne sono
	
	public abstract Iterator<A> adiacenti(int v); 	//MATRICE: theta(n) 
													//LISTA: theta(gradoUscita(v))
	//le complessità degli iteratori comprendono la creazione dell'iteratore, while it.hasNext{it.next}
	//queste insieme di istruzioni porta ad esplorare tutto il grafo e per la lista è pari al grado di uscita
	
	public abstract void aggiungiArco(A a);// MATRICE: theta(1) //LISTA: media theta(1) peggiore theta(gradoUscita(a.in))

	public abstract boolean rimuoviArco(A a);// MATRICE: theta(1) //LISTA: media theta(1) peggiore theta(gradoUscita(a.in))

	public abstract boolean arco(A a);// MATRICE: theta(1) //LISTA: media theta(1) peggiore theta(gradoUscita(a.in))

	public abstract boolean arco(int v1, int v2);// MATRICE: theta(1) //LISTA: media theta(1) peggiore theta(gradoUscita(a.in))


	// SPAZIALE O(n)
	public List<Integer> depthFirstSearch(int nodoPartenza)
	{	List<Integer> risultato=new ArrayList<Integer>();
		boolean[] marcati=new boolean[n];
		for(int i=0;i<n;i++)
			marcati[i]=false;
		depthFirstSearch(nodoPartenza, risultato, marcati);
		return risultato;
	}
	
	
	// MATR. Theta(n^2) LISTA Theta(m)
	// SPAZIALE Theta(n)
	protected void depthFirstSearch(int nodoPartenza, List<Integer> risultato, 
			boolean[] visitati)
	{	risultato.add(nodoPartenza);  							//theta(1)
		visitati[nodoPartenza]=true;  							//theta(1)
		Iterator<A> itAdiacenti=adiacenti(nodoPartenza);		//theta(1)
		while(itAdiacenti.hasNext())							// MATR. Theta(n) LISTA Theta(gout(n))
		{	int ad=itAdiacenti.next().getFin();
			if(!visitati[ad])
				depthFirstSearch(ad,risultato,visitati);
		}
	}
	
	// MATR. Theta(n^2) LISTA Theta(m)
	// SPAZIALE O(n)
	public List<Integer> breadthFirstSearch(int nodoPartenza)
	{	ArrayList<Integer> risultato=new ArrayList<Integer>();
		boolean[] visitati=new boolean[n];
		for(int i=0;i<n;i++)
			visitati[i]=false;
		Queue<Integer> coda=new LinkedList<Integer>();
		risultato.add(nodoPartenza);
		visitati[nodoPartenza]=true;
		coda.offer(nodoPartenza);
		while(!coda.isEmpty())  // viene iterato n volte
		{	int nodo=coda.poll();
			Iterator<A> itAdiacenti=adiacenti(nodo);
			while(itAdiacenti.hasNext())						// MATR. Theta(n) LISTA Theta(gout(n))
			{	int ad=itAdiacenti.next().getFin();
				if(!visitati[ad])
				{	risultato.add(ad);
					visitati[ad]=true;
					coda.offer(ad);
				}
			}
		}
		return risultato;		
	}
	
	public List<Double> dijkstra(int nodoPartenza) //costo uguale a primm
	{ 	Double[] distanze=new Double[n];
		for(int i=0;i<n;i++)
			distanze[i]=Double.POSITIVE_INFINITY; //assegno inizialmente infinito come distanza dalla radice
		boolean[] raggiunti=new boolean[n];
		for(int i=0;i<n;i++)
			raggiunti[i]=false;
		distanze[nodoPartenza]=0.0;
		int nodoCorrente=nodoPartenza;
		while(nodoCorrente!=-1)
		{	raggiunti[nodoCorrente]=true;
			Iterator<A> adnn=adiacenti(nodoCorrente);
			while(adnn.hasNext())
			{	A a=adnn.next();
				if(!raggiunti[a.getFin()])
				{double nuovaDist=distanze[nodoCorrente]+pesoArco(a);//unica istruzione diversa da primm
		//per nuovaDistanza si intende la somma tra il peso dell'arco e la distanza calcolata precedentemente
					if(nuovaDist<distanze[a.getFin()])
						distanze[a.getFin()]=nuovaDist;
				}
			}
			nodoCorrente=-1;
			double minPeso=Double.POSITIVE_INFINITY;
			for(int i=0;i<n;i++)
				if(!raggiunti[i] && distanze[i]<minPeso)
				{	nodoCorrente=i;
					minPeso=distanze[i];
				}
		}
		return Arrays.asList(distanze);
	}
	
	
	//Theta(n^2)
	//SPAZIALE  Theta(n)
	public boolean eAciclicoGO(){ 
		int[] gradi = calcolaGradiEntrata(); // MATR. theta(n^2) LISTA Theta(n+m)
		boolean[] rimossi = new boolean[n()]; 
		int daRimuovere = cercaNodoGradoZeroNonRimosso(gradi, rimossi); //Theta(n)
		while(daRimuovere!=-1){ // per tutti i nodi - n volte  //Theta(n^2)
			//RIMUOVIAMO IL NODO daRimuovere
			rimossi[daRimuovere]= true;
			Iterator<A> it = adiacenti(daRimuovere);
			while (it.hasNext())							// MATR. Theta(n) LISTA Theta(gout(daTrimuovere))
				gradi[it.next().getFin()]--; 
			
			//CERCHIAMO IL PROX NODO DA RIMUOVERE
			daRimuovere = cercaNodoGradoZeroNonRimosso(gradi, rimossi); // Theta(n)
		}
		if (tuttiRimossi(rimossi))	// theta(n)
			return true;
		return false;
	}
	
	private int[] calcolaGradiEntrata() {
		int[] gradi = new int[n()];
		Iterator<A> it = archi();
		while (it.hasNext())
			gradi[it.next().getFin()]++;
		return gradi;
	}

	private boolean tuttiRimossi(boolean[] rimossi) {
		for(int i=0; i<rimossi.length;i++)
			if(!rimossi[i])
				return false;
		return true;
	}

	private int cercaNodoGradoZeroNonRimosso(int[] gradi, boolean[] rimossi) {
		for(int i= 0; i<gradi.length; i++)
			if(!rimossi[i]&&(gradi[i]==0))
				return i;
		return -1;
	}

	public List<Double> prim()
	{	int nodoPartenza=0;
		Double[] pesi=new Double[n];
		for(int i=0;i<n;i++)
			pesi[i]=Double.POSITIVE_INFINITY;
		boolean[] raggiunti=new boolean[n];
		for(int i=0;i<n;i++)
			raggiunti[i]=false;
		int[] padri = new int[n()];
		pesi[nodoPartenza]=0.0;
		padri[0]=0;
		int nodoCorrente=nodoPartenza; // theta(n) per inizializzare le strutture dati
		while(nodoCorrente!=-1) //finche ci sono nodi
		{	raggiunti[nodoCorrente]=true;
			Iterator<A> adnn=adiacenti(nodoCorrente); //iteratore dei nodi adiacenti
			while(adnn.hasNext()) 
//scorrere l'iteratore dipende da come viene rappresentato il grafo:
//MATRIX: theta(n) LIST: theta(gout(nodocorrente)) quindi theta(n^2) oppure theta(n+m)
// con heap e lista adiacenza theta(n+m*log n)
			{	A a=adnn.next();
				if(!raggiunti[a.getFin()])
				{	double nuovoPeso=pesoArco(a);
					if(nuovoPeso<pesi[a.getFin()]){ //confronto con altri modi (il migliore)per arrivare ad a
						pesi[a.getFin()]=nuovoPeso;
						padri[a.getFin()]=nodoCorrente;
					}
				}
			}
			nodoCorrente=-1;
			double minPeso=Double.POSITIVE_INFINITY;
			for(int i=0;i<n;i++) // theta(n^2) con heap theta(n log n) 
				if(!raggiunti[i] && pesi[i]<minPeso)
				{	nodoCorrente=i;
					minPeso=pesi[i];
				}
		}//arrivato qui ho che l'istruzione piu costosa e' il for che costa theta(n^2)
		return Arrays.asList(pesi);
	}//costo theta(n^2) per grafi non sparsi theta(n+m*log n) per grafi sparsi 
	
	
	
	
	
	
	//implementazione non molto efficiente
	public Grafo<ArcoPesato> kruskal(){ // theta( m n) ||  usa  (union find) theta(m lg n)
		//preleva un array di archi ordinati in base al loro peso
		ArcoPesato[] archi = generaArchiOrdinati();//il costo con il migliore algoritmo sarebbe m log m
		//ma siccome vale m<=n^2 allora vale anche m log m <= m log n^2
		//O(m log m) implica che valga O(m log n^2) e applicando la proprietà dei logaritmi O(2m log n)->
		//-> O(m log n)
		// rappresentata a listatheta(m lg n) 
		//rappresentata a matrice di adiacenza theta(n^2 + m lg n) sommati perche non si sa quale
		//sia il più grande
		int inseriti = 0;
		GrafoLista<ArcoPesato> albero = new GrafoLista<ArcoPesato>(n());
		//grafo vuoto con n archi
		// creare union find - theta(n)
		
		//theta (m + n lg n)
		//si scorre il vettore dei nodi dal piu piccolo al piu grande
		for(int i=0; (i<archi.length)&& (inseriti<n()-1); i++){ // m volte
			//lancio una visita
			List<Integer> lista = albero.depthFirstSearch(archi[i].getIn()); //FACCIO M VOLTE LA VISITA
			//QUESTA è l'operazione che ci caratterizza il costo dell'algoritmo!
			// theta(n) perchè la variabile albero ha n archi! || theta (n^2)
			// 2 find theta(1)
			//se raggiungo il nodo finale ritorno la foresta altrimenti lo aggiungo come arco 
			if (!lista.contains(archi[i].getFin())){ // theta(1)
				albero.aggiungiArco(archi[i]); // theta(1)
				inseriti++; // theta(1)
				// union degli insiemi che contengono archi[i].getFin()
				// e archi[i].getIn() - theta(n)
				// complessivamente facciamo n-1 union che hanno un costo theta(n lg n)
			}
		}
		if (inseriti==n()-1)
			return albero;
		return null;
	}
	
	private ArcoPesato[] generaArchiOrdinati() {
		// TODO Auto-generated method stub
		return null;
	}

	public double[][] floydWarshall()
	{	double[][] distanze=new double[n][n]; //matrice di adiacenze
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				if(i==j) //nella diagonale metto zero anche se non è corretto
					distanze[i][j]=0;
				else
					distanze[i][j]=Double.POSITIVE_INFINITY; //infinito dove non c'è nessun arco
		Iterator<A> it=archi();
		while(it.hasNext())
		{	
			A a=it.next();
			distanze[a.getIn()][a.getFin()]=pesoArco(a); 
			//metto il peso nella posizione corrispondente della matrice
		}
		//il vero algoritmo sono questi 3 for innestati
		for(int k=0;k<n;k++)
			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++)
					if(distanze[i][j]>distanze[i][k]+distanze[k][j])
						distanze[i][j]=distanze[i][k]+distanze[k][j];
			//per ogni coppia di nodi aggiorno la matrice delle distanze secondo la formula vista
			//aggiornamento in-place. N.B. versione poco migliorata che evita di creare un array di matrici
		return distanze;
	} //COSTO theta(n^3) triviale in quanto ci sono 3 for innestati. 
	//costo spaziale è theta(n^2) perchè allochiamo la matrice. 
	//la matrice è si l'area di lavoro che risultato, quindi non è un grosso spreco di spazio
	
	protected double pesoArco(A a)
	{	if(a instanceof ArcoPesato)
			return ((ArcoPesato)a).getPeso();
		return 1.0;
	}

	public int getN() {
		return n;
	}

	public int getM() {
		return m;
	}
	
	
}
