package poo.progettoBigInt;
import java.util.Iterator;

//Autore Matteo Orlando, matricola:213430

public interface BigInt extends Comparable<BigInt>, Iterable<Integer>{

	default String value() {  //ritorna il valore del Bigint sottoforma di string 
		//utilizzo la classe StringBuilder per rappresentare il numero
		StringBuilder sb= new StringBuilder(300);
		Iterator<Integer> it= this.iterator();
		while(it.hasNext()) {
			int i=it.next();			
			sb.append(String.valueOf(i));
		}
		return sb.toString();
	}
	
	default int length() { //ritorna il numero di cifre di questo BigInt
		int c=0;
		Iterator<Integer> it=iterator();
		while(it.hasNext()) {
			it.next();
			c++;
		}
		return c;
	}
	
	BigInt factory(int x); 
	
	BigInt factory(String s);
	
	
	//NB SI PUO' USARE L'ITERATOREEEEEEE
	default BigInt incr() {
		//aggiungo 1 al BigInt, se dovessi avere un riporto continuo con l'incremento
		String n=this.value();
		boolean flag=false; //indica se non ho pi� un riporto
		int i=n.length()-1;
		while(i>=0 && !flag) {
			char v=n.charAt(i);
			int t=Character.getNumericValue(v);
			t++;
			if(i==0 && t==10) {
				n="10"+n.substring(i+1);
				break;
			}
			if(n.length()==1 && t==10) {
				n="10";
				break;
			}
			if(t==10) {
				n=n.substring(0,i)+'0'+n.substring(i+1);
			}
			else {
				//n=n.replace(String.valueOf(v), String.valueOf(t));
				n=n.substring(0,i)+t;
				flag=true;
			}
				
			i--;
		}
		return factory(n);
	}
	
	default BigInt decr() { //eccezione se this = zero
		String n=this.value();
		if(n.equals("0"))
			throw new IllegalArgumentException();	
		//sottraggo 1 al BigInt se dovessi avere un riporto continuo a decrementare
		boolean flag=false; //indica se non ho pi� un riporto
		int i=n.length()-1;
		while(i>=0 && !flag) {
			char v=n.charAt(i);
			int t=Character.getNumericValue(v);
			t--;
			if(n.length()==1) {
				n=n.replace(String.valueOf(v), String.valueOf(t));
				break;
			}
			if(i==0 && t==0) {
				n=n.substring(1);
				break;
			}
			if(t==-1) {
				n=n.substring(0,i)+'9'+n.substring(i+1);
			}
			else{
				n=n.substring(0,i)+t;
				flag=true;
			}
				
			i--;
		}
		return factory(n);
	}
	
	BigInt add(BigInt a); //effettua la somma tra this e m
	
	BigInt sub(BigInt s); //ritorna un bigint con la differenza tra this e s con this>=s
	
	BigInt mul(BigInt m); // effettua la moltiplicazione tra this e m
	
	BigInt div(BigInt d); //ritorna il quoziente della divisione intera tra this e d con this>=d
	
	BigInt rem(BigInt d); //ritorna il resto della divisione intera tra this e d con this>=d
	
	BigInt pow(	int exp); //calcola la potenza this^exp	
}
