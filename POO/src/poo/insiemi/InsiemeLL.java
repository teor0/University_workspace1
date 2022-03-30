package poo.insiemi;
import java.util.*;
import poo.util.Vector;
public class InsiemeLL<T> extends InsiemeAstratto<T> {
	private LinkedList<T> lista= new LinkedList<>();
	
	@Override
	public int size() {
		return lista.size();
	}
	
	@Override
	public boolean eVuoto() {
		return size()==0;
	}
	
	public InsiemeLL<T> crea(){
		return new InsiemeLL<T>();
	}
	
	@Override
	public boolean appartiene(T e) {
		if(lista.contains(e))
			return true;
		return false;
	}
	
	public boolean aggiungi(T e) {
		if(!appartiene(e)) {
			lista.add(e);
			return true;
		}
		return false;
	}
	
	
	public boolean rimuovi(T e) {
		if(appartiene(e)) {
			lista.remove(e);
			return true;
		}
		return false;
	}
	
	public InsiemeLL<T> unione(Insieme<T> altro){
		InsiemeLL<T> ret= crea();
		for(T e:altro)
			if(!ret.appartiene(e))
				ret.aggiungi(e);
		for(T e:this)
			if(!ret.appartiene(e))
				ret.aggiungi(e);
		return ret;
	}
	
	public InsiemeLL<T> intersezione(Insieme<T> altro){
		InsiemeLL<T> ret=crea();
		ListIterator<T> lit=this.lista.listIterator();
		while(lit.hasNext()) {
			T e=lit.next();
			if(altro.appartiene(e)&& !ret.appartiene(e))
				ret.aggiungi(e);
		}
		return ret;
	}
	
	public InsiemeLL<T> differenza(Insieme<T> altro){
		InsiemeLL<T> ret=crea();
		ListIterator<T> lit=lista.listIterator();
		while(lit.hasNext()) {
			T e=lit.next();
			if(!altro.appartiene(e))
				ret.aggiungi(e);
		}
		return ret;
	}
	
	public InsiemeLL<T> differenzaSimmetrica(Insieme<T> altro){
		/*InsiemeLL<T> un=this.unione(altro);
		InsiemeLL<T> in=this.intersezione(altro);
		InsiemeLL<T> ret=un.differenza(in);*/
		return this.unione(altro).differenza(this.intersezione(altro));
	}
	
	public Iterator<T> iterator(){
		return lista.iterator();
	}
	
	 public static void main(String[] args) {
		 Insieme<Integer> a= new InsiemeLL<Integer>();
		 Insieme<Integer> b= new InsiemeLL<Integer>();
		 a.aggiungi(1);
		 a.aggiungi(2);
		 a.aggiungi(3);
		 a.aggiungi(4);
		 a.aggiungi(5);
		 a.aggiungi(7);
		 a.aggiungi(9);
		 b.aggiungi(2);
		 b.aggiungi(4);
		 b.aggiungi(6);
		 b.aggiungi(8);
		 b.aggiungi(10);
		 System.out.println(a);
		 System.out.println(b);
		 System.out.println();
		 System.out.println(a.unione(b));
		 System.out.println(a.intersezione(b));
		 System.out.println();
		 System.out.println(a.differenza(b));
		 System.out.println(a.differenzaSimmetrica(b));
	 }
	
	
}
