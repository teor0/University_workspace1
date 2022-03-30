package poo.contatori;

public class Contatore {
	protected int valore;
	
	public Contatore() {
		valore=0;
	}
	
	public Contatore(int valore) {
		this.valore=valore;
	
	}
	public Contatore(Contatore c) {
		this.valore=c.valore;
	}
	public int getValore() {
		return valore;
	}
	
	public void incr() {
		valore++;
	}
	
	public void decr() {
		valore--;
	}
	
	public String toString() {
		return "Contatore di valore: " + valore;
	}
	
	public boolean equals(Object o) {
		if(o == this)
			return true;
		if(!(o instanceof Contatore))
			return false;
		Contatore c= (Contatore) o;
		return this.valore==c.valore;
	}
	
	
	
	

}

