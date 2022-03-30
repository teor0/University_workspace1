package poo.progettoBigInt;
import java.math.BigInteger;
import java.util.*;

//Autore Matteo Orlando, matricola:213430

public class BigIntLL extends AbstractBigInt{
	
	private LinkedList<Integer> lista = new LinkedList<>();
	
	@Override
	public int length() {
		//ritorna la lunghezza della linkedlist
		return lista.size();
	}
	
	@Override
	public Iterator<Integer> iterator(){
		//ritorna l'iteratore della lista come iteratore
		return lista.iterator();
	}
	
	public BigIntLL(String s) {
		//costruisce un BigIntLL a partire da una stringa
		String pattern= "\\d+";
		char p=s.charAt(0);	
		if(!s.matches(pattern) || (Character.getNumericValue(p)==0 && s.length()>1))
			throw new IllegalArgumentException("La stringa contiene caratteri non permessi");
		for(int i=0; i<s.length(); i++) {
			int y=Character.getNumericValue(s.charAt(i));
			this.lista.add(y);
		}
	}
	
	public BigIntLL() {
		//costruisce un BigIntLL con lista vuota
		this.lista.clear();
	}
	
	public BigIntLL(BigInt x) {
		//costruisce un bigintLL per copia
		this(x.value());
	}
	
	
	public BigIntLL(int x) {
		//crea un BigIntLL a partire da un intero x
		this(String.valueOf(x));
	}
	
	@Override
	public BigInt factory(int x) {
		//crea un BigInt a partire da un intero x
		return new BigIntLL(x);
	}
	
	@Override
	public BigInt factory(String s) {
		return new BigIntLL(s);
	}
	
	public BigIntLL add(BigInt a) {
		//effettua l'addizione in colonna tra 2 BigInt
		//creo risultato vuoto ed una copia BigIntLL di a
		BigIntLL addendo= new BigIntLL(a);
		BigIntLL ret= new BigIntLL();
		BigIntLL t= new BigIntLL(this.value());
		if(addendo.length()>this.length()) {
			ListIterator<Integer> tmp= t.lista.listIterator();
			int l= addendo.length()-t.length();
			for(int i=0; i<l; i++)
				tmp.add(0);
		}
		
		if(this.length()>addendo.length()) {
			ListIterator<Integer> tmp= addendo.lista.listIterator();
			int l= this.length()-addendo.length();
			for(int i=0; i<l; i++)
				tmp.add(0);
		}
		
		//una volta che si hanno un numero ci cifre uguali si pu� effettuare l'addizione
		
		ListIterator<Integer> it1=t.lista.listIterator(t.length());
		ListIterator<Integer> it2=addendo.lista.listIterator(addendo.length());
		int riporto=0;
		while(it2.hasPrevious()) {
			ListIterator<Integer> it3=ret.lista.listIterator();
			int s1=it1.previous();
			int s2=it2.previous();
			s2+=riporto;
			int s=s1+s2;
			if(it2.previousIndex()==-1 && s>=10) {
				it3.add(s%10);
				it3.previous();
				it3.add(s/10);
				break;
			}//if
			if(s>=10) {
				it3.add(s%10);
				riporto=s/10;
			}
			else {
				it3.add(s);
				riporto=0;
			}
		}//while
		return ret;
	}//add
		
	public BigIntLL sub(BigInt s) { //ritorna un bigint con la differenza tra this e s con this>=s
		BigIntLL sott= new BigIntLL(s);
		BigIntLL ret=new BigIntLL();
		if(this.compareTo(s)<0)
			throw new ArithmeticException("Operazione impossibile!");
		if(sott.length()<lista.size()) {
			ListIterator<Integer> tmp=sott.lista.listIterator();
			for(int i=0; i<=lista.size()-sott.length(); i++)
				tmp.add(0);
		}
		
		//dato che il numero di cifre sono uguali si pu� effettuare la sottrazione
		
		ListIterator<Integer> it1= this.lista.listIterator(lista.size());
		ListIterator<Integer> it2= sott.lista.listIterator(sott.length());
		int riporto=0;
		while(it2.hasPrevious()) {
			ListIterator<Integer> it3= ret.lista.listIterator();
			int s1=it1.previous();
			int s2=it2.previous();
			s1-=riporto;
			int sot=s1-s2;
			if(it1.previousIndex()==-1 && s1==0)
				break;
			if(sot<0) {
				it3.add(sot+10);
				riporto=1;
			}
			else {
				it3.add(sot);
				riporto=0;
			}
		}//while
		
		//rimozione di eventuali zeri che non fanno parte del valore vero e proprio ad 09 diventa 9
		ListIterator<Integer> v=ret.lista.listIterator();
		int pc=v.next();
		if(pc==0) {
			v.remove();
			while(v.hasNext()){
				int ic=v.next();
				if(ic>0)
					break;
				v.remove();
			}
		}
		return ret;
	}//sub
	
	
	public BigIntLL mulSin(ListIterator<Integer> x) {
		//moltiplicazione in colonna di un BigInt per un singolo intero x
		BigIntLL ret= new BigIntLL();
		ListIterator<Integer> it1= this.lista.listIterator(lista.size());
		int riporto=0;
		int m2=x.previous();
			while(it1.hasPrevious()) {
				ListIterator<Integer> it2= ret.lista.listIterator();
				int m1=it1.previous();
				int pr=(m1*m2)+riporto;
				int t=pr%10;
				it2.add(t);
				riporto=pr/10;
				if(it1.previousIndex()==-1 && riporto>0){
					it2.previous();
					it2.add(riporto);
				}
			}//while
		//rimozione di eventuali 0
		ListIterator<Integer> v=ret.lista.listIterator();
		int pc=v.next();
		if(pc==0) {
			v.remove();
			while(v.hasNext()){
				int ic=v.next();
				if(ic>0)
					break;
				if(ic==0 && !v.hasNext())
					break;
				v.remove();
			}
		}
		return ret;
	}//mulSin
	
	public BigIntLL mul(BigInt m) {	// effettua la moltiplicazione tra this e m
		if(m.compareTo(new BigIntLL("0"))==0)
			return new BigIntLL(0);
		BigIntLL ris= new BigIntLL(0);
		BigIntLL fattore= new BigIntLL(m.value());
		ListIterator<Integer> it1= fattore.lista.listIterator(fattore.length());
		int c=0;
		while(it1.hasPrevious()){
			BigIntLL pr=this.mulSin(it1);
			if(c>0)			
				for(int i=0; i<c; i++) {
					ListIterator<Integer> tmp=pr.lista.listIterator(pr.length());
					tmp.add(0);
				}//for
			ris=pr.add(ris);
			c++;
		}//while
		return ris;
	}//mul
	
	public BigIntLL div(BigInt d) { //ritorna il quoziente della divisione intera tra this e d con this>=d
		if(this.compareTo(d)<0 || d.value().equals("0"))
			throw new ArithmeticException("Divisione non possibile!");
		BigIntLL resto= new BigIntLL(0);
		BigIntLL quo= new BigIntLL();
		BigIntLL divisore= new BigIntLL(d);
		BigIntLL dtmp= new BigIntLL();
		boolean flag=false; //false finch� non abbiamo fatto una volta la divisione
		ListIterator<Integer> it1= this.lista.listIterator();
		ListIterator<Integer> it2= quo.lista.listIterator();
		//per effettuare la divisione cerchiamo il multiplo pi� vicino
		while(it1.hasNext()) {
			ListIterator<Integer> it3= dtmp.lista.listIterator(dtmp.length());
			it3.add(it1.next());
			if(dtmp.compareTo(new BigIntLL(0))==0) {
				it2.add(0);
				resto= new BigIntLL(0);
			}
			if(dtmp.compareTo(divisore)<0 && flag) {
				it2.add(0);
			}
			if(dtmp.compareTo(divisore)>=0){
				//abbiamo trovato il multiplo che ci permette di effettuare la divisione ed aggiungiamo il risultato al quoziente
				flag=true;
				BigIntLL q= new BigIntLL(divisore.mpv(dtmp));
				BigIntLL risultato=divisore.mul(q);
				for(Integer x:q)
					it2.add(x);
				resto= new BigIntLL(dtmp.sub(risultato));
				if(resto.compareTo(new BigIntLL(0))>0)
					dtmp=new BigIntLL(resto);
				else {
					dtmp=new BigIntLL();
				}
			}//if
		}//while
		return quo;
	}//div
	
	private BigInt mpv(BigInt dividendo){ //ritorna il multiplo pi� vicino che permette la divisione 
		//inizializzo il risultato a this perch� contenuto almeno una volta se minore del dividendo
		BigIntLL ret= new BigIntLL(this);
		BigInt i= new BigIntLL(1);
		//ricerca del multiplo pi� vicino
		while(dividendo.compareTo(ret)>0){
			i=i.incr();
			ret=this.mul(i);
			if(ret.compareTo(dividendo)==0)
				return i;
			if(ret.compareTo(dividendo)>0) { //se ottengo un multiplo maggiore del dividendo torno al precedente multiplo e mi fermo
				i=i.decr();
				break;
			}
		}
		return i;
	}//mpv
	
	public BigIntLL rem(BigInt d) { //ritorna il resto della divisione intera tra this e d con this>=d
		if(this.compareTo(d)<0)
			throw new ArithmeticException("Divisione non possibile!");
		BigIntLL quoziente= new BigIntLL(this.div(d));
		BigIntLL prodotto= new BigIntLL(d.mul(quoziente));
		//semplicemente svolgiamo la differenza tra this e d che moltiplica il quoziente della divisione
		return this.sub(prodotto);
	}//rem
	
	public BigIntLL pow(int exp) { //calcola la potenza this^exp
		if(exp==0)
			return new BigIntLL(1);
		if(exp==1)
			return this;
		BigIntLL ret= new BigIntLL(this.value());
		BigIntLL pow= new BigIntLL(this.value());
		//moltiplico this per se stesso exp volte
		for(int i=1; i<exp; i++)
			ret=ret.mul(pow);
		return ret;
	}//pow
	
	public static void main(String[] args) {
		BigIntLL a= new BigIntLL("2");
		BigInteger a1= new BigInteger("2");
		System.out.println("Potenza con BigInt: "+ a.pow(128));
		System.out.println("Potenza con BigInteger: "+ a1.pow(128));
		System.out.println();
		BigIntLL k= new BigIntLL("57");
		BigIntLL b= new BigIntLL("19547814248234378");
		BigIntLL c= new BigIntLL("18");
		BigIntLL o= new BigIntLL("281252191");
		BigIntLL d=new BigIntLL("127929314881793");
		BigIntLL f=new BigIntLL("999");
		BigIntLL e= new BigIntLL("134");
		System.out.println("Operazioni varie");
		System.out.println(b + " - " + e +" = " + b.sub(e));
		System.out.println(c+ " + " + f +" = "+ c.add(f));
		System.out.println(d+ "/"+f + " = "+ d.div(f));
		BigIntLL j=new BigIntLL(f.rem(k));
		System.out.println("Resto della divisione: " + j);	
		System.out.println(b+ "*" + o + " = "+ b.mul(o));
	}
	
	
	
	
	
}
