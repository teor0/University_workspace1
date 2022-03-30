package variantiAB;

import java.util.concurrent.Semaphore;
public class ABDecrementoA {
	

	private static Semaphore sA=new Semaphore(7);
	private static Semaphore sB=new Semaphore(0);
	private static int countA=0;
	private static int stdf=7;
	private static Semaphore mutex=new Semaphore(1);
	
	static class A extends Thread{	
		public void run() {
			try {
				sA.acquire();
				mutex.acquire();
				System.out.print("A");
				countA++;
				if(countA==stdf) {
					sB.release();
				}
				mutex.release();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	static class B extends Thread{	
		public void run() {
			try {
				sB.acquire();
				System.out.print("B ");
				countA=0;
				stdf--;
				sA.release(stdf);		
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		while(true) {
			new A().start();new A().start();
			new A().start();
			new A().start();
			new B().start();
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
