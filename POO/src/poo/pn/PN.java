package poo.pn;
import java.util.*;
public class PN {

	private TreeMap<String, Posto> M;
	private LinkedList<Transizione> T;
	private LinkedList<Transizione> abilitate;
	private LinkedList<Transizione> disabilitate;
	
	public PN(TreeMap<String, Posto> m, LinkedList<Transizione> t) {
		this.M=m;
		this.T=t;
		abilitate=new LinkedList<>();
		disabilitate=new LinkedList<>();
		for(Transizione tr:T) {
			if(tr.abilitata())
				abilitate.add(tr);
			else
				disabilitate.add(tr);
		}
			
	}
	
	public void singleStep() {
		if(abilitate.size()>0) {
			Collections.shuffle(abilitate);
			Transizione t=abilitate.removeFirst();
			t.sparo();
			System.out.println(M.toString());
			disabilitate.add(t);
			ListIterator<Transizione> lit=abilitate.listIterator();
			while(lit.hasNext()) {
				Transizione cur=lit.next();
				if(!cur.abilitata()) {
					lit.remove();
					disabilitate.add(cur);
				}
			}
			ListIterator<Transizione> lit2=disabilitate.listIterator();
			while(lit2.hasNext()) {
				Transizione cor=lit2.next();
				if(cor.abilitata()) {
					lit2.remove();
					abilitate.add(cor);
				}
			}
		}
		else {
			System.out.println("Deadlock!");
			return;
		}
	}
	
	public void multipleStep(int n) {
		for(int i=0; i<n; i++)
			singleStep();
	}
	
	
}
