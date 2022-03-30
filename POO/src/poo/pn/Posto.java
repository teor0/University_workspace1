package poo.pn;

public class Posto extends Entita{

	private int marcatura;
	private String nome;
	
	public Posto(String nome) {
		super(nome);
		this.nome=nome;
		marcatura=0;
	}
	
	public Posto(String nome, int marcatura) {
		super(nome);
		if(marcatura<0)
			throw new IllegalArgumentException();
		this.nome=nome;
		this.marcatura=marcatura;
	}
	
	public Posto(Posto p) {
		super(p.nome);
		this.nome=p.getNome();
		marcatura=p.getMarcatura();
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getMarcatura() {
		return marcatura;
	}
	
	public void setMarcatura(int m) {
		if(m<0)
			throw new IllegalArgumentException();
		marcatura=m;
	}
	
	@Override
	public String toString() {
		String s= nome +"#"+ marcatura;
		return s;
	}
	
}
