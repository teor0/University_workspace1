package poo.esempi;
import java.util.*;

public class TestLista {
	public static void main (String... args) {
		List<String> l=new LinkedList<String>();
		for(String x: args) {
			boolean flag=false;
			ListIterator<String> lit=l.listIterator(l.size());
			while(lit.hasPrevious() && !flag) {
				String s=lit.previous();
				if(s.compareTo(x)<=0) {
					lit.next();
					lit.add(x);
					flag=true;
				}//if
			}//while
			if(!flag)
				lit.add(x);
		}//for
		System.out.println(l);
	}//main
}
