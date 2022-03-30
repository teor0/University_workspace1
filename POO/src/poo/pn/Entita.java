package poo.pn;

public abstract class Entita {
	
	String nome;
	public Entita(String nome) {
		if(nome==null)
			throw new IllegalArgumentException();
		this.nome=nome;
	}
	
	public String toString() {
		return nome;
	}
	
	public int hashCode() {
		final int M=83;
		int h=0;
		h=nome.hashCode()*M;
		return h;
	}
	
}
