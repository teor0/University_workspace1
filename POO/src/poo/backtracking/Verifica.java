package poo.backtracking;
import java.util.*;
public class Verifica extends Backtracking<Integer,String>{
	
	//data una stringa tale stringa deve essere scomponibile in una successione di parole
	//questo puo' accedere pero' solo se le parole che compongono la stringa sono 
	//all'interno di un dizionario di parole ammissibili
	
	private String S;
	private Set<String> diz;
	private String[] aux;
	private boolean stop=false;
	
	public Verifica(Set<String> d, String s) {
		diz=new HashSet<>(d);
		this.S=s;
		aux=new String[diz.size()];
	}
	
	@Override
	protected List<Integer> puntiDiScelta() {
		ArrayList<Integer> ps=new ArrayList<>();
		//i punti di scelta sono gli indici dell'array aux
		for(int i=0; i<aux.length; i++)
			ps.add(i);
		return ps;
	}
	
	@Override
	protected Collection<String> scelte(Integer p) {
		ArrayList<String> s=new ArrayList<>();
		int j=0; //posizione su S
		for(int i=0; i<p; i++)
			j=j+aux[i].length();//somma delle lunghezze delle parole su aux
		for(int k=j; k<=S.length(); k++) {
			String parola=S.substring(j,k);//prendo la parola
			if(diz.contains(parola))
				s.add(parola);
		}
		return s;
	}

	@Override
	protected boolean assegnabile(Integer p, String s) {
		return diz.contains(s); //assegnabile se diz contiene la stringa
	}

	@Override
	protected void assegna(Integer p, String s) {
		aux[p]=s; 
	}

	@Override
	protected void deassegna(Integer ps, String s) {
		
	}
	
	protected boolean esisteSoluzione(Integer p) {
		String s="";
		for(int i=0; i<=p; i++)
			s=s+aux[i];
		return s.equals(S); //creo la decomposizione completa
	}

	@Override
	protected void scriviSoluzione(Integer p) {
		for(int i=0; i<=p; i++) 
			System.out.print(aux[i]+" ");
		System.out.println();
		stop=true;
	}
	
	protected boolean ultimaSoluzione(Integer p) {
		return stop;
	}

	@Override
	protected void risolvi() {
		tentativo(puntiDiScelta(), 0);
	}
	
	public static void main(String[] args) {
		HashSet<String> d=new HashSet<>(java.util.Arrays.asList("il", "dado", "lento",
				"la", "cane", "corre", "zebra", "veloce", "gatto", "grigio", "treno", "salta"));
		String s="ilgattolentosaltailcane";
		new Verifica(d,s).risolvi();
		System.out.println("fine");
	}
	
}
