package poo.razionali;

/**
I numeri razionali.
Sono dotati delle principali funzioni dei razionali,
tra le quali somma, differenza, rapporto, reciproco, ecc.
La classe genera oggetti mutabili.
*/

public class Razionale implements Comparable<Razionale>
{	/**
    Il razionale viene rappresentato mediante
    una coppia di interi.
    Il denominatore è sempre positivo.
    Il numero è sempre ridotto ai minimi termini.
    */
	private int numeratore;
	private int denominatore;
	private static int contaRazionali=0; //variabili della classe che è condivisa da tutti i razionali
	/**
    Costruisce un razionale a partire da
    due interi.
    @param numeratore il numeratore del razionale
    @param denominatore il denominatore del razionale.
    Il denominatore deve essere diverso da 0.
    */
    public Razionale(int numeratore, int denominatore){
    	if(denominatore == 0)
			throw new DenominatoreNullo();
		this.numeratore = numeratore;
		this.denominatore = denominatore;
		if(this.denominatore < 0)
		{	this.numeratore *= -1;
			this.denominatore *= -1;			
		}
		semplifica();
		contaRazionali++;
	}//costruttore
	
    /**
    Semplifica l'oggetto.
    */
    private void semplifica()
	{	if(numeratore == 0)
			denominatore = 1;
		else
		{	int divisore = mcd(Math.abs(numeratore),denominatore);
			numeratore /= divisore;
			denominatore /= divisore;
		}
	}
	
    /**
    Calcola il massimo comun divisore di due numeri.
    @param a uno dei numeri.
    @param b l'altro numero.
    @return il massimo comun divisore di a e b.
    */
    private static int mcd(int a, int b)                 //è un metodo helper, che si utilizza solo nella classe
	{	if(b==0)
			return a;
		return mcd(b, a%b);
	}
    
    // esempio: mcd(10,4) ritorna mcd(4,2) che ritorna mcd(2,0) che ritorna il risultato 2
	
    /**
    Costruisce un razionale a partire da un intero.
    @param n l'intero dal quale costruiamo il razionale.
    */
    public Razionale(int n)
	{	this(n,1);
		contaRazionali++;
	}
	
    /**
    Costruttore di default. 
    Genera il razionale 0.
    */
    public Razionale()
	{	this(0);
		contaRazionali++;		
	}
	
    /**
    Costruttore per copia.
    Costruisce un razionale identico a quello ricevuto.
    @param r il razionale identico a quello che vogliamo costruire.
    */
    public Razionale(Razionale r)
	{	this.numeratore = r.numeratore;
		this.denominatore = r.denominatore;
		contaRazionali++;
	}
	
	/**
    Restituisce il numeratore dell'oggetto.
    @return il numeratore dell'oggetto.
    */
    public int getNumeratore()
	{	return numeratore;		
	}

    /**
    Restituisce il denominatore dell'oggetto.
    @return il denominatore dell'oggetto.
    */
    public int getDenominatore()
	{	return denominatore;		
	}
	
    /**
    Rappresenta l'oggetto come stringa.
    @return una stringa che rappresenta l'oggetto.
    */
    public String toString()
	{	if(denominatore == 1)
			return ""+numeratore;
		return numeratore+"/"+denominatore;		
	}

    /**
    Verifica se l'oggetto è uguale ad un altro razionale.
    @param o un oggetto che deve essere un razionale.
    @return true, se l'oggetto è uguale
    ad o; false, altrimenti.
    */
    public boolean equals(Object o)
	{	if(o == null)
			return false;
		if(o == this)
			return true;
		if(!(o instanceof Razionale))
			return false;
		Razionale r = (Razionale)o;
		return numeratore == r.numeratore && denominatore == r.denominatore;
	}
	
	/**
    Moltiplica l'oggetto per un razionale.
    @param r il razionale da moltiplicare.
    */
    public void moltiplica(Razionale r)
	{	numeratore *= r.numeratore;
		denominatore *= r.denominatore;
		semplifica();
	}
	
    /**
    Moltiplica l'oggetto per un intero.
    @param n l'intero da moltiplicare.
    */
    public void moltiplica(int n)
	{	moltiplica(new Razionale(n));
	}
	
	/**
    Aggiunge un razionale all'oggetto.
    @param r il razionale da aggiungere.
    */
    public void aggiungi(Razionale r)
	{	numeratore = numeratore * r.denominatore + denominatore * r.numeratore;
		denominatore *= r.denominatore;
		semplifica();		
	}
	
    /**
    Sottrae un razionale dall'oggetto.
    @param r il razionale da sottrarre.
    */
    public void sottrai(Razionale r)
	{	Razionale s = new Razionale(-1 * r.numeratore, r.denominatore);
		aggiungi(s);		
	}
	
    /**
    Calcola il reciproco dell'oggetto.
    @return il reciproco dell'oggetto.
    */
    public Razionale reciproco()
	{	return new Razionale(denominatore, numeratore);		
	}
	
    /**
    Divide l'oggetto per un razionale.
    @param r il razionale per cui dividere l'oggetto.
    */
    public void dividi(Razionale r)
	{	moltiplica(r.reciproco());		
	}
	
	/**
    Eleva l'oggetto ad una potenza intera.
    @param p la potenza a cui elevare l'oggetto.
    */
    public void eleva(int p)
	{	numeratore = (int)Math.pow(numeratore, p);
		denominatore = (int)Math.pow(denominatore, p);
	}
	
    /**
    Calcola la somma di due razionali.
    @param r1 un razionale.
    @param r2 un razionale.
    @return un nuovo razionale pari alla somma di r1 ed r2.
    */
    public static Razionale somma(Razionale r1, Razionale r2)
	{	Razionale ret = new Razionale(r1);
		ret.aggiungi(r2);
		return ret;	
	}
	
    /**
    Calcola la differenza di due razionali.
    @param r1 un razionale.
    @param r2 un razionale.
    @return un nuovo razionale pari alla differenza tra r1 ed r2.
    */
    public static Razionale differenza(Razionale r1, Razionale r2)
	{	Razionale ret = new Razionale(r1);
		ret.sottrai(r2);
		return ret;	
	}
	
    /**
    Calcola il prodotto di due razionali.
    @param r1 un razionale.
    @param r2 un razionale.
    @return un nuovo razionale pari al prodotto di r1 ed r2.
    */
    public static Razionale prodotto(Razionale r1, Razionale r2)
	{	Razionale ret = new Razionale(r1);
		ret.moltiplica(r2);
		return ret;	
	}
	
    /**
    Calcola il rapporto di due razionali.
    @param r1 un razionale.
    @param r2 un razionale diverso da 0.
    @return un nuovo razionale pari al rapporto tra r1 ed r2.
    */
    public static Razionale rapporto(Razionale r1, Razionale r2)
	{	Razionale ret = new Razionale(r1);
		ret.dividi(r2);
		return ret;	
	}
	
	/**
    Calcola la potenza intera di un razionale.
    @param r un razionale.
    @param p la potenza intera.
    @return un nuovo razionale pari a r elevato p.
    */
    public static Razionale eleva(Razionale r, int p)
	{	Razionale ret = new Razionale(r);
		ret.eleva(p);
		return ret;	
	}	
	
    /**
    Confronta l'oggetto con un altro razionale.
    @param r il razionale con cui fare il confronto.
    @return il risultato del confronto, 
    per come previsto dall'interfaccia Comparable.
    */
    @Override
    public int compareTo(Razionale r)
	{	int sinistra = numeratore * r.denominatore;
		int destra = r.numeratore * denominatore;
		if(sinistra < destra)
			return -1;
		if(sinistra > destra)
			return 1;
		return 0;		
	}
    /**
     Ritorna l'hashCode del razionale.
     @return il codice hashCode.
     Effettua lo shuffling dei campi numeratore e denominatore secondo un numero primo.
     */
    @Override
    public int hashCode() {
    	final int M=83;
    	//shuffling dei campi numeratore e denominatore secondo il numero primo M
    	int h=0;
    	h=h*M+numeratore;
    	h=h*M+denominatore;
    	return h;
    }
    /**
     Dice al garbage collector di decrementare il numero di razionali
     una volta che elimina l'oggetto non più utilizzato.
     */
    protected void finalize() {
    	contaRazionali--;
    }
}

