package laghettoS;
import java.util.Random;
public abstract class Laghetto {

	protected static final int IMMETTI_PESCI=10;
	protected final int MAX_PESCI, MIN_PESCI;
	protected int pesci;
	protected static final int PESCATORE=0,ADDETTO=1;
	
	public Laghetto(int max, int min) {
		MAX_PESCI=max;
		MIN_PESCI=min;
		pesci=new Random().nextInt(MAX_PESCI-MIN_PESCI+1)+MIN_PESCI;
	}
	
	public abstract void inizia(int t);
	public abstract void finisci(int t);
	
	protected void pesciAttuali() {
		System.out.println("Pesci attuali "+ pesci);
	}
	
	public void test(int pescatori, int addetti){
		Persona[] pe=new Persona[pescatori];	
		Persona[] ad=new Persona[addetti];
		for(int i=0; i<pe.length; i++)
			pe[i]=new Persona(0,this);
		for(int j=0; j<ad.length; j++)
			ad[j]=new Persona(1,this);
		
		for(int i=0; i<pe.length; i++)
			pe[i].start();
		for(int j=0; j<ad.length; j++)
			ad[j].start();
			
		
		}
	
}
