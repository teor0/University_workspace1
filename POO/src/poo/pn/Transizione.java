package poo.pn;
import java.util.*;
public class Transizione extends Entita{
	
	private LinkedList<ArcoIn> preset= new LinkedList<>();
	private LinkedList<ArcoOut> postset=new LinkedList<>();
	private String nome;
	
	
	public Transizione(String nome, LinkedList<ArcoIn> in, LinkedList<ArcoOut> out){
		super(nome);
		this.nome=nome;
		this.preset=new LinkedList<>(in);
		this.postset=new LinkedList<>(out);
	}
	
	public boolean abilitata() {
		for(ArcoIn a:preset) {
			if(a.getPeso()>a.getPosto().getMarcatura())
				return false;
		}
		return true;
	}
	
	public void sparo() {
		if(abilitata()) {
			for(ArcoIn a:preset){
				a.getPosto().setMarcatura(a.getPosto().getMarcatura()-a.getPeso());
			}
			for(ArcoOut ar:postset) {
				ar.getPosto().setMarcatura(ar.getPeso());
			}	
		}
		else {
			throw new RuntimeException();
		}
	}
	
	
	
}
