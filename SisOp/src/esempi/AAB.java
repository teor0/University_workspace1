package esempi;
import java.util.concurrent.Semaphore;
public class AAB {
	
	private static Semaphore sA=new Semaphore(2);
	private static Semaphore sB=new Semaphore(0);
	
	static class A extends Thread{
		
		public void run() {
			try {
				sA.acquire();//faccio una stampa quindi decremento di 1
				System.out.print("A");
				sB.release();//rilascio fino a quando ho 2
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	static class B extends Thread{
		
		public void run() {
			try {
				sB.acquire(2);//ho 2 quindi posso stampare B
				System.out.print("B ");
				sA.release(2);//rilascio 2 per stampare 2 A
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		while(true) {
			new A().start();
			new A().start();
			new B().start();
			new B().start();
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
}
