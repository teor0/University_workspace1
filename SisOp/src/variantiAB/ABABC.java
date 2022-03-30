package variantiAB;

import java.util.concurrent.Semaphore;

import variantiAB.ABC.A;
import variantiAB.ABC.B;
import variantiAB.ABC.C;

public class ABABC {

	private static Semaphore sA=new Semaphore(1);
	private static Semaphore sB=new Semaphore(0);
	private static Semaphore sC=new Semaphore(0);
	private static Semaphore mutex=new Semaphore(1);
	private static int countA=0;
	private static int countB=0;
	
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
				mutex.acquire();
				System.out.print("B");
				countB++;
				if(countB==1)
					sA.release();
				else if(countB==2)
					sC.release();
				mutex.release();
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
				countB=0;
				sA.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		while(true) {
			new C().start();new C().start();new C().start();new C().start();
			new B().start();
			new B().start();new B().start();new B().start();
			new C().start();
			new A().start();
			new B().start();
			new C().start();
			new A().start();
			new B().start();
			new C().start();
			try {
				Thread.sleep(500);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
