package poo.contatori;

public class ContatoreModulare extends Contatore {
	protected int modulo;
	
	public ContatoreModulare() {
		super();
		modulo=10;
	}
	
	public ContatoreModulare(int modulo) {
		super(); //non è necessario visto che la fa java in automatico
		if(modulo<=1)
			throw new IllegalArgumentException();
		this.modulo=modulo;
	}
	
	public ContatoreModulare(int valore, int modulo) {
		super(valore);
		if(valore<0||valore>=modulo||modulo<=1)
			throw new IllegalArgumentException();
		this.modulo=modulo;
	}
	
	public ContatoreModulare(ContatoreModulare cm) {
		super(cm.valore);
		this.modulo=cm.modulo;
	}
	
	public int getModulo() {
		return modulo;
	}
	
	@Override
	public void incr() {
		valore=(valore+1)%modulo;
	}
	
	@Override
	public void decr() {
		valore=(valore-1+modulo)%modulo;
	}
	@Override
	public String toString() {
		return "Contatore modulo: "+ modulo +" " + super.toString();
	}
	@Override
	public boolean equals(Object o) {
		if(o==this)
			return true;
		if(!(o instanceof ContatoreModulare))
			return false;
		ContatoreModulare cm= (ContatoreModulare) o;
		return valore==cm.getValore() && modulo==cm.getModulo();
	}
	
	@Override
	public int hashCode() {
		final int M=37;
		int h=0;
		h=h*M+valore;
		h=h*M+modulo;
		return h;
	}
	
	public static void main(String [] args) {
		Contatore c=new Contatore();
		ContatoreModulare cm=new ContatoreModulare( 8 );
		System.out.println(c);
		System.out.println(" ");
		System.out.println("10 incrementi da "+cm.getValore());
		for( int i=0; i<10; i++ ){
			cm.incr(); 
			System.out.println( cm );
		}
		System.out.println("10 decrementi da "+cm.getValore());
		for( int i=0; i<10; i++ ){
			cm.decr(); 
			System.out.println( cm );
		}
		System.out.println(" ");
		c=cm;
		System.out.println(c); 
		for( int i=0; i<5; i++ ) 
			c.incr();
		System.out.println(c);
		ContatoreModulare k= new ContatoreModulare(10);
		System.out.println(cm.hashCode());
		System.out.println(k.hashCode()+ " hashCode k");
		System.out.println(c.hashCode());
		System.out.println(cm.equals(k));
	}
	
}//ContatoreModulare