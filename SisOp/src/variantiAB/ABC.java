package variantiAB;

import java.util.concurrent.Semaphore;
public class ABC {

	private static Semaphore sA=new Semaphore(1);
	private static Semaphore sB=new Semaphore(0);
	private static Semaphore sC=new Semaphore(0);
	
	static class A extends Thread{	
		public void run() {
			try {
				sA.acquire();
				System.out.print("A");					
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
				System.out.print("B");
				sC.release();			
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	static class C extends Thread{
		public void run() {
			try {
				sC.acquire();
				System.out.print("C ");
				sA.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		while(true) {
			new A().start();
			new C().start();
			new B().start();
			new A().start();
			new A().start();
			new C().start();
			new C().start();
			new B().start();
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
