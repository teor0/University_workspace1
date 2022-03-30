package poo.polinomi;

public class Monomio implements Comparable<Monomio> {
	private final int COEFF;
	private final int GRADO;
	
	public Monomio(int coeff, int grado) {
		if(grado<0)
			throw new IllegalArgumentException("Grado inferiore di 0");
		this.COEFF=coeff;
		this.GRADO=grado;
	}
	
	public Monomio(Monomio m) {
		this.COEFF=m.COEFF;
		this.GRADO=m.GRADO;
	}
	
	
	public int getGrado() {
		return GRADO;
	}
	
	public int getCoeff() {
		return COEFF;
	}
	
	
	public Monomio moltiplicazione(int s) {
		return new Monomio(this.COEFF*s, GRADO);
	}
	
	public Monomio moltiplicazione(Monomio m) {
		return new Monomio(COEFF*m.COEFF, GRADO+m.GRADO);
	}
	
	public Monomio add(Monomio m) {
		if(!this.equals(m))
			throw new RuntimeException("Monomi non simili");
		return new Monomio(this.COEFF+m.COEFF, this.GRADO);
	}
	
	@Override
	public boolean equals(Object x) {
		if(!(x instanceof Monomio))
			return false;
		if(this==x)
			return true;
		Monomio m= (Monomio) x;
		return this.GRADO==m.GRADO;
	}
	@Override
	public int hashCode() {
		return GRADO;
	}
	
	public int compareTo(Monomio m) {
		if(this.GRADO>m.GRADO)
			return -1;
		if(this.equals(m))
			return 0;
		return 1;
	}
	
	public String toString() {
		StringBuilder sb= new StringBuilder(30);
		if(COEFF==0)
			sb.append(0);
		else {
			if(COEFF<0)
				sb.append('-');
			if(Math.abs(COEFF)!=1 || GRADO==0)
				sb.append(Math.abs(COEFF));
			if(COEFF!=0 && GRADO>0)
				sb.append('x');
			if(COEFF!=0 && GRADO>1) {
				sb.append('^');
				sb.append(GRADO);
			}
		}
		return sb.toString();
	}
	
	public static void main( String [] args) {
		Monomio n=new Monomio(0,10);
		Monomio k= new Monomio(4,2);
		System.out.println(n);
		System.out.println(k);
	}
	
}
