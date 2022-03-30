package poo.listaDiInteri;

public class ListaDiInteri {
	//realizza una lista di interi ordinata per valori crescenti
	
	private static class Nodo{
		 int valore;
		 Nodo next;
	}
	
	private Nodo inizio=null; //lista vuota
	private int size=0;
	
	public void add(int x) {
		Nodo nuovo=new Nodo();
		nuovo.valore=x;
		nuovo.next=null; //superficiale
		if(inizio==null || inizio.valore>=x) {
			nuovo.next=inizio;
			inizio=nuovo;
		}//lista vuoto oppure x da inserire prima del primo elemento
		else {//devo inserire un nodo dopo il primo elemento
			Nodo pre=inizio;// invece di mettere pre a null lo metto ad inizio
			Nodo cor=inizio.next;
			while(cor!=null && cor.valore<x){
				pre=cor;
				cor=cor.next;
			}//finito il while, nuovo deve stare tra pre e cor
			nuovo.next=cor;
			pre.next=nuovo;
		}//lista non vuota oppure x da inserire dopo il primo elemento
		size++;
	}//add
	
	public void remove(int x){//rimuove la prima occorrenza di x
		Nodo pre=null, cor=inizio;
		while(cor!=null && cor.valore<x){
			pre=cor;
			cor=cor.next;
		}
		if(cor!=null && cor.valore==x) {
			if(cor==inizio) {
				inizio=cor.next;
			}
			else {
				pre.next=cor.next; //bypass 
				cor.next=null; //inutile perchè il garbage collector rimuove gli oggetti non riferiti
			}
			size--;
		}
	}
	
	public int size(){
		return size;
		/*int c=0;
		Nodo cor=inizio; //aliasing con il primo nodo
		while(cor!=null) {
			c++;
			cor=inizio.next;
		}
		return c;*/
	}
	
	public boolean contains(int x) {
		Nodo cor=inizio;
		while(cor!=null) {
			if(cor.valore==x)
				return true;
			if(cor.valore>x)
				return false; //essendo la lista ordinata, se cor.valore > x allora mi fermo
			cor=cor.next;
		}
		return false;
	}
	
	public void removeAll(int x) {
		Nodo pre=null, cor=inizio;
		while(cor!=null) {
			if(cor.valore==x) {
				if(cor==inizio)
					inizio=cor.next;
				else {
					pre.next=cor.next;
				}
				cor=cor.next;
				size--;
			}
			else if(cor.valore>x)
				break;
			else {
				pre=cor;
				cor=cor.next;
			}
		}
	}
	
	public int duplicati(int x) {
		Nodo cur=inizio;
		int c=-1;
		while(cur!=null) {
			if(cur.valore==x)
				c++;
			cur=cur.next;
		}
		return c;
	}
	
	public boolean equals(Object o){
		if(!(o instanceof ListaDiInteri))
			return false;
		if(o==this)
			return true;
		ListaDiInteri l= (ListaDiInteri) o;
		if(l.size!=this.size)
			return false;
		Nodo thiscur=inizio;
		Nodo lcur=l.inizio;
		while(thiscur!=null) {
			if(thiscur.valore!=lcur.valore)
				return false;
			thiscur=thiscur.next;
			lcur=lcur.next;
		}
		return true;
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder(50);
		Nodo cor=inizio;
		sb.append("[");
		while(cor!=null) {
			sb.append(cor.valore);
			if(cor.next!=null)
				sb.append(", ");
			cor=cor.next;
		}
		sb.append("]");
		return sb.toString();
	}
	public static void main(String[] args) {
		ListaDiInteri l=new ListaDiInteri();
		l.add(5); l.add(18); l.add(2); l.add(2); l.add(7);
		System.out.println(l.duplicati(2));
		System.out.println(l.toString());
		System.out.println(l.size());
	}
	
}
