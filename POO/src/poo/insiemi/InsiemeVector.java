package poo.insiemi;

import java.util.Iterator;

import poo.util.*;

public class InsiemeVector<T> extends InsiemeAstratto<T>{
	
	private Vector<T> v= new ArrayVector<T>();
	
	@Override
	public int size() {
		return v.size();
	}
	
	@Override
	public boolean eVuoto() {
		return size()==0;
	}
	
	@Override
	public boolean appartiene(T e) {
		if(v.contains(e))
			return true;
		return false;
	}
	
	public boolean aggiungi(T e) {
		if(!v.contains(e)) {
			v.add(e);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean rimuovi(T e) {
		if(v.contains(e)) {
			v.remove(e);
			return true;
		}
		return false;
	}
	
	public InsiemeVector<T> unione(Insieme<T> altro){
		InsiemeVector<T> ret=new InsiemeVector<T>();
		for(T e:this)
			if(!ret.appartiene(e))
				ret.aggiungi(e);
		for(T e:altro)
			if(!ret.appartiene(e))
				ret.aggiungi(e);
		return ret;	
	}
	
	public InsiemeVector<T> intersezione(Insieme<T> altro){
		InsiemeVector<T> ret= new InsiemeVector<T>();
		for(T e:altro)
			if(this.appartiene(e) && !ret.appartiene(e))
				ret.aggiungi(e);
		return ret;
	}
	
	public InsiemeVector<T> differenza (Insieme<T> altro){
		InsiemeVector<T> ret= new InsiemeVector<T>();
		for(T e: this)
			if(!altro.appartiene(e))
				ret.aggiungi(e);
		return ret;
	}
	
	public InsiemeVector<T> differenzaSimmetrica(Insieme<T> altro){
		return this.unione(altro).differenza(this.intersezione(altro));
	}
		
	public Iterator<T> iterator(){
		return v.iterator();
	}
	
	public static void main(String[] args) {
		 InsiemeVector<Integer> a= new InsiemeVector<Integer>();
		 InsiemeLL<Integer> b= new InsiemeLL<Integer>();
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
