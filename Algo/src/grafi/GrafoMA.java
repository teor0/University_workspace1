/**
 * 
 */
package grafi;

import grafi.exception.VerticeNonEsisteException;

import java.util.Iterator;
import java.util.Set;

/**
 * @author sflesca
 * 
 */
public class GrafoMA<A extends Arco> extends Grafo<A> {

	Arco[][] M;

	/**
	 * @param n
	 */
	public GrafoMA(int n) {
		super(n);
		M = new Arco[n][n];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see grafi.Grafo#archi()
	 */
	@Override
	public Iterator<A> archi() {
		// TODO Auto-generated method stub
		return new IteratorArchi();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see grafi.Grafo#adiacenti(int)
	 */
	@Override
	public Iterator<A> adiacenti(int v) {
		if (!(v >= 0) && (v < M.length))
			throw new VerticeNonEsisteException();
		return new IteratorAdiacenti(v);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see grafi.Grafo#aggiungiArco(grafi.Arco)
	 */
	@Override
	public void aggiungiArco(Arco a) {
		if(a==null) return;
		if (M[a.getIn()][a.getFin()] == null)
			m++;
		M[a.getIn()][a.getFin()] = a;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see grafi.Grafo#rimuoviArco(grafi.Arco)
	 */
	@Override
	public boolean rimuoviArco(Arco a) {
		if(a==null) return false;
		if (M[a.getIn()][a.getFin()] != null) {
			M[a.getIn()][a.getFin()] = null;
			m--;
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see grafi.Grafo#arco(grafi.Arco)
	 */
	@Override
	public boolean arco(Arco a) {
		return M[a.getIn()][a.getFin()] != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see grafi.Grafo#arco(int, int)
	 */
	@Override
	public boolean arco(int v1, int v2) {
		// TODO Auto-generated method stub
		return M[v1][v2] != null;
	}

	protected class IteratorAdiacenti implements Iterator<A> {
		
		//inizialmente mi memorizzo l'arco corrente
		boolean hasNext = true;
		A curr = null;
		int curfin; //sarebbe il corrente nodo finale
		int in;

		public IteratorAdiacenti(int in) {
			this.in = in;
			curfin = -1;
			avanza();
		}
		//hasNext e next riprendono l'iteratore degli alberi
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return hasNext;
		}

		@Override
		public A next() {
			if (!hasNext)
				return null;
			A tmp = curr;
			avanza();
			return tmp;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();

		}
		//tutto il lavoro lo fa avanza
		private void avanza() {
			//aumento curfin che prima era pari a 0 e finché è più piccolo
			//dell'utlima colonna della matrice
			for (curfin++; curfin < M[in].length; curfin++)
				if (M[in][curfin] != null)
					break;// devo restituire questo valore
			if (curfin < M[in].length)
				curr = (A) M[in][curfin]; //il prossimo sarà questo qui da restituire
			else
				hasNext = false; //ho finito gli archi
		}//la complessità è pari a theta (n) nel caso peggiore, ma nel caso peggiore scorro tutta la lista
		//e quindi la prossima hasNext ha false
		//l'analisi ammortizzata ci dice che prima o poi curfin renderà la condizione del for falsa
		//quindi al più gli incrementi di curfin saranno al più n+1. l'operazione dominante è quella
		//quindi si avrà un costo pari a theta(n)
	}

	protected class IteratorArchi implements Iterator<A> {
		
		//invece di creare sempre un nuovo iteratore su ogni riga, mi mantengo solo 1 ovverro quello degli adiacenti
		int currin = 0;
		Iterator<A> it = adiacenti(currin);
		boolean hasNext = true;
		A curr = null;

		/**
		 * 
		 */
		public IteratorArchi() {
			avanza();
		}

		@Override
		public boolean hasNext() {
			return hasNext;
		}

		@Override
		public A next() {
			if (!hasNext)
				return null;
			A tmp = curr;
			avanza();
			return tmp;
		}

		private void avanza() {
			if (it.hasNext())
				curr = it.next();
			else {
				for (currin++; currin < M.length; currin++) { //cerco il prossimo possibile nodo iniziale
					it = adiacenti(currin);
					if (it.hasNext()) {
						curr = it.next();
						break;
					}
				}
				if (currin >= M.length) { //non ho trovato un successivo quindi hasNext ha false
					hasNext = false;
					curr = null;
				}
			}
		} //il costo è pari a: fare la scansione tramite adiacenti che costa thetha(n) più il fatto che bisogna farlo
		//per ogni currin perciò n volte quindi il costo è pari a thetha(n^2)

		@Override
		public void remove() {
			throw new UnsupportedOperationException();

		}

	}
}
