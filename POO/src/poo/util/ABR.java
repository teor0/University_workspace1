package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ABR<T extends Comparable<? super T>> implements CollezioneOrdinata<T>{
	private static class Nodo<E>{
		E info;
		Nodo<E> fS, fD;
		
		public String toString(){
			return info.toString();
		}
	}
	private Nodo<T> radice=null;
	
	public int size(){
		return size(radice);
	}
	
	private int size(Nodo<T> radice){
		if(radice==null)
			return 0;
		return 1+size(radice.fS)+size(radice.fD);
	}
	
	public void add(T x) {//aggiunge i nodi in modo ordinato
		radice=add(radice,x);
	}
	
	private Nodo<T> add(Nodo<T> radice, T x){
		if(radice==null) {
			Nodo<T> n=new Nodo<>();
			n.info=x;
			//n.fS=null; n.fD=null; superficiali
			return n;
		}
		if(radice.info.compareTo(x)>0) {
			radice.fS=add(radice.fS, x); //chiamata ricorsiva del metodo
			return radice;
		}
		radice.fD=add(radice.fD, x);
		return radice;
	}//add
	
	private Nodo<T> addIte(Nodo<T> radice, T x) {
		Nodo<T> cur=radice;
		while(cur!=null) {
			if(cur.info.compareTo(x)>0 && cur.fS==null) {
				Nodo<T> n=new Nodo<>();
				n.info=x;
				cur.fS=n;
				break;
			}
			else if(cur.info.compareTo(x)<0 && cur.fD==null) {
				Nodo<T> n=new Nodo<>();
				n.info=x;
				cur.fD=n;
				break;
			}
			else if(cur.info.compareTo(x)>0 && cur.fS!=null)
				cur=cur.fS;
			else if(cur.info.compareTo(x)<0 && cur.fD!=null) 
				cur=cur.fD;
		}
		if(cur==null) {
			Nodo<T> n=new Nodo<>();
			n.info=x;
			return n;
		}
		return radice;
	}
	
	public void remove(T x) {
		radice=remove(radice,x);
	}
	
	public Nodo<T> remove(Nodo<T> radice, T x){
		if(radice==null)//albero vuoto
			return null;
		if(radice.info.compareTo(x)>0) { //devo andare nel sottoalbero sinistro
			radice.fS=remove(radice.fS, x);
			return radice;
		}
		if(radice.info.compareTo(x)<0) { //devo andare nel sottoalbero destro
			radice.fD=remove(radice.fD, x); 
			return radice;
		}
		//se arrivo qui allora radice.info e' uguale a x
		if(radice.fS==null && radice.fD==null)//caso nodo foglia
			return null;
		if(radice.fS==null) //nodo con solo fD
			return radice.fD;
		if(radice.fD==null) //nodo con solo fS
			return radice.fS;
		//arrivato qui il nodo ha entrambi i figli. bisogna prendere il minimo del sottoalbero destro
		
		//caso in cui il minimo sia la radice del sottoalbero destro ovvero non esiste fS 
		if(radice.fD.fS==null) {
			radice.info=radice.fD.info; //promozione 
			radice.fD=radice.fD.fD; //bypass
			return radice;
		}
		//ricerca minimo in quanto esiste fS
		Nodo<T> padre=radice.fD;
		Nodo<T> figlio=padre.fS;
		while(figlio.fS!=null) {
			padre=figlio;
			figlio=figlio.fS;
		}
		radice.info=figlio.info; //promozione minimo
		//a questo punto il nodo figlio potrebbe avere fD quindi bisogna fare il bypass
		padre.fS=figlio.fD; //bypass
		return radice;
	}
	
	public void inOrder(List<T> l) { //in ordine o visita simmetrica. dal + piccolo al + grande
		inOrder(radice,l);
	}
	
	private void inOrder(Nodo<T> radice, List<T> l) {
		if(radice!=null) {
			inOrder(radice.fS, l); //visito a sinistra
			l.addLast(radice.info); //visita la radice
			inOrder(radice.fD, l);
		}
	}
	
	public void preOrder(List<T> l) {//visita in ordine anticipato radice per prima e poi + piccolo
		preOrder(radice,l);
	}
	
	private void preOrder(Nodo<T> radice,List<T> l) {
		if(radice!=null) {
			l.addLast(radice.info);//radice per prima
			preOrder(radice.fS,l);
			preOrder(radice.fD,l);
		}
	}
	
	public void postOrder(List<T> l) { //prima i figli poi la radice
		postOrder(radice,l);
	}
	
	private void postOrder(Nodo<T> radice,List<T> l) {
		if(radice!=null){
			postOrder(radice.fS, l);
			postOrder(radice.fD,l);
			l.addLast(radice.info);//radice per ultima
		}
	}
	
	public void visitaPerLivelli(List<T> visitati) {
		if(radice==null)
			return;
		LinkedList<Nodo<T>> coda=new LinkedList<>();
		coda.addLast(radice);
		while(!coda.isEmpty()) {
			Nodo<T> r=coda.removeFirst();
			visitati.addLast(r.info);//"visito" il nodo
			if(r.fS!=null)
				coda.addLast(r.fS);
			if(r.fD!=null)
				coda.addLast(r.fD);
		}
	}
	
	
	
	public int altezza() {
		return altezza(radice,-1);
	}
	
	private int altezza(Nodo<T> radice, int h) {
		if(radice==null)
			return h;
		h++;
		int aS=altezza(radice.fS,h);
		int aD=altezza(radice.fD,h);
		h=(aS>aD)? aS:aD; //calcola il massimo tra i 2
		return h;
	}
	
	
	public boolean bilanciato() {
		return bilanciato(radice);
	}
	
	private boolean bilanciato(Nodo<T> radice) {
		if(radice==null)
			return true;
		int sS=size(radice.fS);
		int sD=size(radice.fD);
		if(Math.abs(sS-sD)>1)
			return false;
		return bilanciato(radice.fS) && bilanciato(radice.fD);
	}
	
	public void clear(){
		radice=null;
	}
	
	public boolean contains(T x){
		return contains(radice, x);
	}
	
	private boolean contains(Nodo<T> radice, T x){
		if(radice==null)
			return false;
		if(radice.info.equals(x))
			return true;
		if(radice.info.compareTo(x)>0)
			return contains(radice.fS,x);
		return contains(radice.fD,x);
	}
	
	public T get(T x){
		return get(radice,x);
	}
	
	private T get(Nodo<T> radice, T x){
		if(radice==null)
			return null;
		if(radice.info.equals(x))
			return radice.info;
		if(radice.info.compareTo(x)>0)
			return get(radice.fS,x);
		return get(radice.fD,x);
	}
	
	public boolean isEmpty(){
		return radice==null;
	}
	
	public boolean isFull(){
		return false;
	}
	
	public T getFD(T x) {
		return getFD(radice,x);
	}
	
	private T getFD(Nodo<T> radice, T x) {
		if(radice==null)
			return null;
		if(radice.info.compareTo(x)>0)
			return getFD(radice.fS,x);
		if(radice.info.compareTo(x)<0)
			return getFD(radice.fD,x);
		if(radice.fD==null)
			return null;
		return radice.fD.info;
	}
	
	public T getRadice() {
		if(radice.info==null)
			return null;
		return this.radice.info;
	}
	
	public T getFS(T x) {
		return getFS(radice,x);
	}
	
	private T getFS(Nodo<T> radice, T x) {
		if(radice==null)
			return null;
		if(radice.info.compareTo(x)>0)
			return getFS(radice.fS,x);
		if(radice.info.compareTo(x)<0)
			return getFS(radice.fD,x);
		if(radice.fS==null)
			return null;
		return radice.fS.info;
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder(50);
		sb.append('[');
		Iterator<T> it=this.iterator();
		while(it.hasNext()) {
			sb.append(it.next());
			if(it.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append(']');
		return sb.toString();
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof ABR))
			return false;
		if(o==this)
			return true;
		ABR<T> a=(ABR<T>) o;
		return equals(this.radice, a.radice);
	}
	
	private boolean equals(Nodo<T> r1, Nodo<T> r2) {
		if(r1==null && r2==null)
			return true;
		if(r1==null || r2==null)
			return false;
		if(!r1.info.equals(r2.info))
			return false;
		return equals(r1.fS, r2.fS) && equals(r1.fD, r2.fD);
	}
	
	public int hashCode() {
		return toString().hashCode(); //mi faccio ritornare l'hashCode del toString
	}
	
	public ABR<T> copy(){
		ABR<T> ret=new ABR<>();
		Nodo<T> rad=copy(this.radice);
		ret.radice=rad;
		return ret;
	}
	
	private Nodo<T> copy(Nodo<T> radice){
		if(radice==null)
			return null;
		Nodo<T> rad=new Nodo<>();
		rad.info=radice.info;
		rad.fS=copy(radice.fS);
		rad.fD=copy(radice.fD);
		return rad;
	}
	
	public void build(T[] a){
		//a supposto ordinato per valori crescenti. quindi crea un albero di ricerca
		clear();
		radice=build(a,0,a.length-1);	
	}
	
	private Nodo<T> build(T[] a, int inf, int sup){
		if(inf>sup)
			throw new NoSuchElementException();
		if(inf==sup)
			return null;
		int med=(inf+sup)/2;
		Nodo<T> n=new Nodo<>();
		n.info=a[med];
		n.fS=build(a, inf, med);
		n.fD=build(a,med+1, sup);
		radice=n;
		return radice;
	}
	
	public Iterator<T> iterator(){
		return new ABRIterator();
	}
	
	private class ABRIterator  implements Iterator<T>{
		//iteratore basato su lista
		private LinkedList<T> l=new LinkedList<>();
		private Iterator<T> it=null;
		private T cor;
		
		public ABRIterator() {
			preOrder(l);
			it=l.iterator();
		}
		
		public boolean hasNext() {
			return it.hasNext();
		}
		
		public T next() {
			cor=it.next();//potrebbe sollevare NoSuchElementExcpetion
			return cor;
		}
		
		public void remove() {
			it.remove(); //potrebbe sollevare IllegalStateException
			ABR.this.remove(cor);
			
		}
		
	}
	
	private class ABRStackIterator implements Iterator<T>{
		
		private StackConcatenato<Nodo<T>> stack=new StackConcatenato<>();
		private Nodo<T> cor;
		
		private void caricaStack(Nodo<T> radice) {
			if(radice==null)
				return;
			stack.push(radice);
			while(radice.fS!=null) {
				stack.push(radice.fS);
				radice=radice.fS;
			}
		}
		
		public ABRStackIterator() {
			if(radice!=null)
				caricaStack(radice);
		}
		
		public boolean hasNext() {
			return !stack.isEmpty();
		}
		
		public T next() {
			if(!hasNext())
				throw new NoSuchElementException();
			cor=stack.pop();
			if(cor.fD!=null)
				caricaStack(cor.fD);
			return cor.info;
		}
		
		public void remove() {
			if(cor==null)
				throw new IllegalStateException();
			ABR.this.remove(cor.info);
		}
		
	}
	
	
	
	public static void main( String[] args ) {
		ABR<Integer> abr=new ABR<>();
		/*abr.add(12); 
		abr.add(34); 
		abr.add(-2); 
		abr.add(-4); abr.add(5);
		abr.add(1); abr.add(7); abr.add(38); abr.add(-6); abr.add(8);*/
		abr.add(23); abr.add(-3); abr.add(2); abr.add(10);
		abr.add(5); abr.add(1); abr.add(-4); abr.add(12); abr.add(7); abr.add(6);
		List<Integer> lista=new LinkedList<>(); //lista vuota
		/*System.out.println("pre remove"+ abr.toString());
		System.out.println("c "+c.toString());
		abr.remove(34);
		System.out.println("post remove"+ abr.toString());
		System.out.println("c "+c.toString());
		System.out.println();
		System.out.println();*/
		abr.inOrder(lista);
		System.out.println("radice albero "+abr.getRadice());
		System.out.println("fS "+abr.getFS(abr.getRadice()));
		System.out.println("fD "+abr.getFD(abr.getRadice()));
		System.out.println("inOrder= "+lista);
		lista.clear();
		abr.preOrder(lista);
		System.out.println("preOrder= "+lista);
		lista.clear();
		abr.postOrder(lista);
		System.out.println("postOrder= "+lista);
		System.out.println(abr.altezza());
		lista.clear();
		abr.visitaPerLivelli(lista);
		System.out.println("visita per livelli "+lista);
		lista.clear();
		System.out.println();System.out.println();
		Iterator<Integer> it=abr.iterator();
		while(it.hasNext()) {
			Integer x=it.next();
			System.out.println("fS di "+ x+ " e' "+abr.getFS(x));
			System.out.println("fD di "+ x+ " e' "+abr.getFD(x));
			System.out.println();
			//it.remove();
		}
		System.out.println(lista);
		/*Iterator<Integer> it=abr.iterator();
		while(it.hasNext()) {
			lista.addLast(it.next());
		}
		Iterator<Integer> it2=abr.iterator();
		while(it2.hasNext()) {
			it2.next();			
			it2.remove();
		}*/
		//abr.inOrder(lista);
		//System.out.println(abr);
		//System.out.println(lista);
	}
	
}//ABR
