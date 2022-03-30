package trenino;

public class Impiegato extends Thread{

	private Trenino t;
	
	public Impiegato(Trenino t) {
		this.t=t;
	}
	
	public void run() {
		try {
			while(true) {
				t.impFaiSalire();
				t.impMuovi();
				if(t.scatti>=10)
					t.impFaiScendere();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
