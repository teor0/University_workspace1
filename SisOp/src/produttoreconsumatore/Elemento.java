package produttoreconsumatore;

public class Elemento {
	
	private int i;

	public Elemento(int i) {
		super();
		this.i = i;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
	
	public String toString() {
		return ""+i;
	}
}
