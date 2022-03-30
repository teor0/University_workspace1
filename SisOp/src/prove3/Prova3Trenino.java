package prove3;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
public class Prova3Trenino {

	public static Semaphore sem=new Semaphore(1);
	public static void main(String[] args) {
		MyThread3[] m=new MyThread3[8];
		for(int i=0; i<m.length; i++)
			m[i]=new MyThread3(i,m);
		for(int j=0; j<m.length; j++)
			m[j].start();
	}


	static class MyThread3 extends Thread{
		
		private int id; private MyThread3[] threads;
		
		public MyThread3(int id, MyThread3[] t) {
			this.id=id;
			threads=t;
		}
		
		public void run() {
			try {
				TimeUnit.SECONDS.sleep(1);
				if(id%2==0)
					sem.acquire();
				System.out.println("T "+id+ " "+ getState() );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
	
	
	
	

