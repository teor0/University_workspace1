package barbiere;

public class Cliente extends Thread{
	
	private Sala s;
	private int ID;
	
	public Cliente(Sala s, int id) {
		ID=id;
		this.s=s;
	}
	
	public void run() {
		try {
			System.out.format("Il cliente %d vuole tagliarsi i capelli%n", ID);
			boolean ret = s.attendiTaglio();
			if (ret)
				System.out.format("Il cliente %d è riuscito a tagliarsi i capelli%n", ID);
			else
				System.out.format("Il cliente %d abbandona la sala%n", ID);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
