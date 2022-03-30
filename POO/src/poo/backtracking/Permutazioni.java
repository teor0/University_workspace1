package poo.backtracking;

import java.util.*;

public class Permutazioni extends Backtracking<Integer, Integer>{
	
	private Integer[] a;
	private Integer[] b;
	private int numSol=0;
	
	public Permutazioni(Integer[] x){
		TreeSet<Integer> t= new TreeSet<>();
		for(Integer y:x) {
			if(!t.contains(y))
				t.add(y);
			else
				throw new RuntimeException("Array con doppioni");
		}
		a=new Integer[x.length];
		System.arraycopy(x, 0, a, 0, a.length);
		b=new Integer[a.length];
	}

	@Override
	protected boolean assegnabile(Integer p, Integer s) {
		for(int j=0; j<p; j++)
			if(b[j]==a[s])
				return false;
		return true;		
	}

	@Override
	protected void assegna(Integer ps, Integer s) {
		b[ps]=a[s];
	}

	@Override
	protected void deassegna(Integer ps, Integer s) {}

	@Override
	protected void scriviSoluzione(Integer p) {
		numSol++;
		System.out.println("Soluzione:"+ Arrays.toString(b));
		System.out.println();
	}

	@Override
	protected List<Integer> puntiDiScelta() {
		ArrayList<Integer> ps= new ArrayList<>();
		for(int i=0; i<b.length; i++)
			ps.add(i);
		return ps;
	}

	@Override
	protected Collection<Integer> scelte(Integer p) {
		List<Integer> scelte=new ArrayList<>();
		for(int i=0; i<a.length; i++)
			scelte.add(i);
		return scelte;
	}
	
	public boolean esisteSoluzione(Integer p) {
		return p==b.length-1;
	}
	
	/*public boolean ultimaSoluzione(Integer p) {
		return numSol==4;
	}*/

	@Override
	public void risolvi() {
		tentativo(puntiDiScelta(), 0);
	}

	public static void main(String [] args) {
		Integer[] v= {1,2,3};
		Permutazioni p=new Permutazioni(v);
		p.risolvi();
	}
	
	
	
}
