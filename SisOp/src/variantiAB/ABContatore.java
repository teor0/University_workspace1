package variantiAB;
import java.util.concurrent.Semaphore;
public class ABContatore {
	
	private static Semaphore sA=new Semaphore(2);
	private static Semaphore sB=new Semaphore(0);
	private static int countA=0;
	private static int countB=0;
	private static Semaphore mutex=new Semaphore(1);
	
	static class A extends Thread{	
		public void run() {
			try {
				sA.acquire();
				mutex.acquire();//necessario per evitare interleaving
				System.out.print("A");
				countA++;
				if(countA==2) {
					//potrebbe capitare che qui 2 thread arrivino con countA e rilascino sB 2 volte
					countB=0;
					sB.release(2);
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
				mutex.acquire();
				System.out.print("B");
				countB++;
				if(countB==2) {
					System.out.println("\n");
					countA=0;
					sA.release(2);
				}
				mutex.release();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		while(true) {
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
