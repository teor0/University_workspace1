package esempi;

import java.util.concurrent.Semaphore;
//classe che stampa prima A e poi B
public class AB {
	
	private static Semaphore sA=new Semaphore(1);
	private static Semaphore sB=new Semaphore(0);
	static class A extends Thread{
		public void run() {
			try {
				sA.acquire();
				System.out.println("A");
				sB.release();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	static class B extends Thread{
		public void run() {
			try {
				sB.acquire();
				System.out.println("B");
				sA.release();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		while(true) {
			new B().start();
			new A().start();	
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
			}
		}
	}

}
