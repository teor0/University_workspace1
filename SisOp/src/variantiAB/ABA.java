package variantiAB;

import java.util.concurrent.Semaphore;
public class ABA {

	private static Semaphore sA=new Semaphore(1);
	private static Semaphore sB=new Semaphore(0);
	private static int countA=0;
	private static Semaphore mutex=new Semaphore(1);
	
	static class A extends Thread{	
		public void run() {
			try {
				sA.acquire();
				mutex.acquire();
				System.out.print("A");	
				countA++;
				if(countA==1)
					sB.release();
				else if(countA==2) {
					countA=0;
					System.out.print(" ");
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
				System.out.print("B");
				sA.release(2);			
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
