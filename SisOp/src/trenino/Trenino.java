package trenino;

public abstract class Trenino {

	protected int scatti=0;
	protected final int NUM_CABINE=10;
	protected final int NUM_TUR_CABINA=10;
	//cabine[i] indica quanti turisti ci sono nella cabina i
	protected int[] cabina=new int[NUM_CABINE];
	protected int cabinaSuolo=0;
	
	public Trenino() {
		for(int i=0; i<cabina.length; i++)
			cabina[i]=0;
	}
	
	
	public abstract void turSali() throws InterruptedException;
	public abstract void turScendi() throws InterruptedException;
	public abstract void impFaiScendere() throws InterruptedException;
	public abstract void impFaiSalire() throws InterruptedException;
	public abstract void impMuovi() throws InterruptedException;
	
	public String toString() {
		StringBuilder st=new StringBuilder(100);
		st.append("\n");
		st.append("[");
		for(int i=0; i<cabina.length; i++) {
			st.append(cabina[i]);
			st.append(" ");
		}
		st.append("]");
		st.append("\n");
		return st.toString();
	}
	
	public void test(int turisti){
		Impiegato im=new Impiegato(this);
		Turista[] tur=new Turista[turisti];
		for(int i=0; i<tur.length; i++) 
			tur[i]=new Turista(this);
		im.start();
		for(int i=0; i<tur.length; i++) 
			tur[i].start();
	}
}
