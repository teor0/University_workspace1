package esempi;

public class TestStampanteT {

	public static void main(String[] args) throws InterruptedException {
		StampanteT s1=new StampanteT(1,5);
		StampanteT s2=new StampanteT(6,10);
		s1.start();
		s1.join();
		s2.start();
	}
	
}
