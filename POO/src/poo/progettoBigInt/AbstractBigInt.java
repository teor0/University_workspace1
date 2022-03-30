package poo.progettoBigInt;
import java.util.*;
//Autore Matteo Orlando, matricola:213430
public abstract class AbstractBigInt implements BigInt{
	
	@Override
	public String toString() {
		//essendo value la rappresentazione a string del BigInt, risfrutto il metodo per creare il toString
		return this.value();
	}
	
	
	@Override
	public boolean equals(Object x) {
		//l'equals si basa su verificare che ogni singola cifra di this è uguale ad ogni cifra di x
		if(!(x instanceof BigInt))
			return false;
		if(x==this)
			return true;
		BigInt i= (BigInt) x;
		if(i.length()!=this.length())
			return false;
		Iterator<Integer> it1= this.iterator();
		Iterator<Integer> it2= i.iterator();
		while(it2.hasNext()) {
			int c1=it1.next();
			int c2=it2.next();
			if(c1!=c2)
				return false;
		}
		return true;
	}
	
	public int compareTo(BigInt b) {
		//il compareTo inizia il confronto dalla cifra più significativa fino ad arrivare alla meno significativa
		if(this.length()>b.length())
			return 1;
		else if(this.length()<b.length())
			return -1;
		Iterator<Integer> it1= this.iterator();
		Iterator<Integer> it2= b.iterator();
		while(it1.hasNext()) {
			int c1=it1.next();
			int c2=it2.next();
			if(c1>c2)
				return 1;
			if(c1<c2)
				return -1;
			}
		return 0;
	}
	
	@Override
	public int hashCode() {
		//ritorna l'hashcode per this
		final int M=83;
		int h=0;
		Iterator<Integer> it=this.iterator();
		while(it.hasNext()) {
			int i= it.next().hashCode();
			h=h+M*i;
		}
		return h;
	}
	
}
