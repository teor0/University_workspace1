package poo.pn;

import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		TreeMap<String, Posto> M=new TreeMap<>();
		LinkedList<Transizione> t=new LinkedList<>();
		Posto p1=new Posto("p1",1);
		Posto p2=new Posto("p2",0);
		Posto p3=new Posto("p3",0);
		Posto p4=new Posto("p4",0);
		ArcoIn a1=new ArcoIn(p1);
		ArcoIn a2=new ArcoIn(p2);
		ArcoIn a3=new ArcoIn(p2);
		ArcoIn a4=new ArcoIn(p3);
		ArcoIn a5=new ArcoIn(p4);
		ArcoIn a6=new ArcoIn(p4);
		LinkedList<ArcoIn> pr1=new LinkedList<>();
		pr1.add(a1);
		LinkedList<ArcoIn> pr2=new LinkedList<>();
		pr2.add(a2);
		LinkedList<ArcoIn> pr3=new LinkedList<>();
		pr3.add(a3);
		LinkedList<ArcoIn> pr4=new LinkedList<>();
		pr4.add(a4);
		pr4.add(a5);
		LinkedList<ArcoIn> pr5=new LinkedList<>();
		pr5.add(a6);
		ArcoOut ao1=new ArcoOut(p2);
		ArcoOut ao2=new ArcoOut(p3);
		ArcoOut ao3=new ArcoOut(p4);
		ArcoOut ao4=new ArcoOut(p1);
		ArcoOut ao5=new ArcoOut(p1);
		LinkedList<ArcoOut> po1=new LinkedList<>();
		po1.add(ao1);
		LinkedList<ArcoOut> po2=new LinkedList<>();
		po2.add(ao2);
		LinkedList<ArcoOut> po3=new LinkedList<>();
		po3.add(ao3);
		LinkedList<ArcoOut> po4=new LinkedList<>();
		po4.add(ao4);
		LinkedList<ArcoOut> po5=new LinkedList<>();
		po5.add(ao5);
		Transizione t1=new Transizione("t1",pr1,po1);
		Transizione t2=new Transizione("t2", pr2,po2);
		Transizione t3=new Transizione("t3", pr3,po3);
		Transizione t4=new Transizione("t4", pr4,po4);
		Transizione t5=new Transizione("t5", pr5,po5);
		t.add(t1);
		t.add(t2);
		t.add(t3);
		t.add(t4);
		t.add(t5);
		M.put("p1", p1);
		M.put("p2", p2);
		M.put("p3", p3);
		M.put("p4", p4);
		System.out.println(M.toString());
		System.out.println(t.toString());
		PN pn=new PN(M,t);
		pn.multipleStep(5);
	}
}
