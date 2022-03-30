package barMod;

public abstract class Bar {

	protected final int CASSA=0, BANCONE=1;
	protected final int FILE=2;
	protected final int[] MAX_IN_FILA= {1,4};
	protected int[] postiLiberi=new int[FILE];
	
	public Bar() {
		for(int i=0; i<FILE; i++)
			postiLiberi[i]=MAX_IN_FILA[i];
	}
	
	public abstract int scegli() throws InterruptedException;
	public abstract void inizia(int i) throws InterruptedException;
	public abstract void finisci(int i) throws InterruptedException;
	
	public void test(int persone) throws InterruptedException{
		for(int i=0; i<persone; i++)
			new Thread(new Cliente(this),"T"+i).start();
	}
	
}
