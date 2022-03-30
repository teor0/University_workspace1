package poo.esempi;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.*;
import poo.polinomi.*;
import poo.util.PoliUtil;
import poo.util.*;
public class TestPoli {
	public static void main( String[] args ) throws IOException {
		String c=JOptionPane.showInputDialog("Insert");
		System.out.println();
		System.out.println(c);
		Polinomio k=PoliUtil.validazione(c);
		System.out.println(k);
		System.out.println();
		Polinomio p=new PolinomioSet();
		p.add( new Monomio(-3,0) );
		p.add( new Monomio(4,2) );
		p.add( new Monomio(-7,5) );
		p.add( new Monomio(-9,2) );
		p.add( new Monomio(3,5) );
		p.add( new Monomio(0,8) );
		System.out.println(p);
		Monomio m=new Monomio(5,2);
		p=p.mul(m);
		System.out.println(p);
		Polinomio q=new PolinomioSet();
		q.add( new Monomio(6,2) );
		q.add( new Monomio(7,5) );
		q.add( new Monomio(-2,4) );
		q.add( new Monomio(1,7) );
		q.add( new Monomio(3,0) );
		System.out.println(q);
		Polinomio ps=p.add(q);
		Polinomio pp=p.mul(q);
		System.out.println();
		System.out.println(p+" + "+q+" = "+ps);
		System.out.println("("+p+") * ("+q+") = "+pp);
		String nf=JOptionPane.showInputDialog("Inserisci il nome del file dove salvare");
		LinkedList<Polinomio> l= new LinkedList<>();
		l.add(q);
		l.add(p);
		l.add(k);
	}//main
}//TestPoli

