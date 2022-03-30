package poo.util;

import java.util.ListIterator;
import java.util.StringTokenizer;

public class AlberoEspressione {
	private static class Nodo{
		Nodo fS, fD;
	}
	private static class NodoOperando extends Nodo{
		int val;
		public String toString(){
			return ""+val;
		}
	}
	private static class NodoOperatore extends Nodo{
		char op;
		public String toString() {
			return ""+op;
		}
	}
	private Nodo radice=null;
	
	public void build( String expr ) {
		//expr contiene una espressione eventualmente con ( ) senza priorità degli operatori della matematica
		if( !expr.matches("(\\d+|[\\+\\-\\*/\\(\\)])+") ) 
			throw new IllegalArgumentException(); //CN
		StringTokenizer st=new StringTokenizer(expr,"+-*/()",true);
		radice=buildEspressione(st);
	}
	
	private Nodo buildOperando( StringTokenizer st ) {
		String tk=st.nextToken();
		if( tk.equals("(") ) 
			return buildEspressione(st);
		int val=Integer.parseInt(tk);
		NodoOperando opnd=new NodoOperando();
		opnd.val=val;
		return opnd;
	}
	private Nodo buildEspressione( StringTokenizer st ) {
		Nodo radice=buildOperando(st);
		while( st.hasMoreTokens() ) {
			char op=st.nextToken().charAt(0);
			if( op==')' ) return radice;
			NodoOperatore nop=new NodoOperatore();
			nop.op=op;
			nop.fS=radice;
			Nodo opnd=buildOperando(st);
			nop.fD=opnd;
			radice=nop;
		}
		return radice;
	}
	
	public void postOrder() {//genera la RPN dell'espressione
		postOrder( radice );
		System.out.println();
	}
	public void postOrder( Nodo radice ) {
		if( radice!=null ) {
			postOrder( radice.fS );
			postOrder( radice.fD );
			System.out.print(radice+" ");
		}
	}
	public void preOrder() {
		preOrder( radice );
		System.out.println();
	}
	private void preOrder( Nodo radice ) {
		if( radice!=null ) {
			System.out.print( radice+" " );
			preOrder( radice.fS );
			preOrder( radice.fD );
		}
	}
	
	public void inOrder() {
		inOrder( radice );
		System.out.println();
	}
	private void inOrder( Nodo radice ) {
		if( radice!=null ) {
			if( radice instanceof NodoOperatore ) 
				System.out.print("(");
			inOrder( radice.fS );
			System.out.print( radice );
			inOrder( radice.fD );
			if( radice instanceof NodoOperatore ) 
				System.out.print(")");
		}
	}
	
	public void inOrder( List<String> lista ) {
		inOrder( radice, lista );
	}//inOrder
	private void inOrder( Nodo radice, List<String> lista ) {
		if( radice!=null ) {
			if( radice instanceof NodoOperatore ) 
				lista.addLast("(");
			inOrder( radice.fS, lista );
			lista.addLast( radice.toString() );
			inOrder( radice.fD, lista );
			if( radice instanceof NodoOperatore ) 
				lista.addLast(")");
		}		
	}//inOrder
	
	public int valore() {
		if( radice==null ) throw new RuntimeException("Nessuna Espressione!");
		return valore( radice );
	}
	private int valore( Nodo radice ) {
		if( radice instanceof NodoOperando ) 
			return ((NodoOperando)radice).val;
		int opnd1=valore(radice.fS);
		int opnd2=valore(radice.fD);
		char op=((NodoOperatore)radice).op;
		switch( op ) {
		case '+': return opnd1+opnd2;
		case '-': return opnd1-opnd2;
		case '*': return opnd1*opnd2;
		default : return opnd1/opnd2;
		}
	}
	
	public String toString() {
		//RIFARE usando l'iteratore di l, ed evitando di generare [ e ]
		List<String> l=new LinkedList<>();
		inOrder(l);
		//return l.toString();
		ListIterator<String> lit=l.listIterator();
		StringBuilder st= new StringBuilder(40);
		while(lit.hasNext()) {
			st.append(lit.next());
			if(lit.hasNext())
				st.append(",");
		}
		return st.toString();
	}
	
	public boolean equals( Object o) {
		if(!(o instanceof AlberoEspressione))
			return false;
		if(o==this)
			return true;
		AlberoEspressione al= (AlberoEspressione) o;
		return equals(this.radice, al.radice );
	}
	
	private boolean equals(Nodo r1, Nodo r2) {
		if(r1==null && r2==null)
			return true;
		if( r1==null || r2==null ) 
			return false;
		if(r1 instanceof NodoOperando && r2 instanceof NodoOperando) {
			NodoOperando rr1=(NodoOperando) r1;
			NodoOperando rr2=(NodoOperando) r2;
			if(!(rr1.val==rr2.val))
				return false;
		}
		else if(r1 instanceof NodoOperatore && r2 instanceof NodoOperatore) {
			NodoOperatore no1=(NodoOperatore) r1;
			NodoOperatore no2=(NodoOperatore) r2;
			if(!(no1.op==no2.op))
				return false;
		}
		else {
			return false;
		}
		return equals(r1.fS,r2.fS) && equals(r1.fD, r2.fD);
	}
	
	public int hashCode() {
		String s=this.toString();
		return s.hashCode();
	}
	
	public static void main( String[] args ) {
		AlberoEspressione ae=new AlberoEspressione();
		AlberoEspressione al=new AlberoEspressione();
		String expr="(2+(3*4))*((17/2)-5)";
		String exp="(2+(5*4))*((17/2)-5)";
		ae.build(expr);
		al.build(exp);
		System.out.println(ae);
		System.out.println(al);
		boolean t=al.equals(ae);
		System.out.println(t);
		System.out.println("Espressione originale "+expr+" ");
		System.out.println("Espressione in versione preFix");
		ae.preOrder();
		System.out.println("Espressione in versione postFix (o RPN)");
		ae.postOrder();
		System.out.println("Espressione in versione inFix");
		ae.inOrder();
		System.out.println("valore("+expr+")="+ae.valore());
	}
}//AlberoEspressione
