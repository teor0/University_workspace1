package poo.polinomi;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;
public class PolinomioSet extends PolinomioAstratto{
	
	private Set<Monomio> monomi= new TreeSet<>();
	
	public int size() {
		return monomi.size();
	}
	
	public Iterator<Monomio> iterator(){
		return monomi.iterator();
	}
	
	public PolinomioSet crea() {
		return new PolinomioSet();
	}
	
	public void add(Monomio m) {
		if(m.getCoeff()==0)
			return ;
		Iterator<Monomio> it= monomi.iterator();
		Monomio ms=null;
		while(it.hasNext()) {
			Monomio mcorr=it.next();
			if(mcorr.equals(m)) {
				it.remove();
				ms=mcorr;
				break;
			}
		}//while
		if(ms!=null) {
			ms=ms.add(m);
			if(ms.getCoeff()!=0)
				monomi.add(ms);
		}
		else
			monomi.add(m);
	}
}
