package esame1606;

import java.util.concurrent.Semaphore;

public class Prova3 {
	
	static int var1, var2;
	static Semaphore mutex=new Semaphore(1);
	
	public static void main(String[] args) throws InterruptedException{
		MyThread[] m=new MyThread[7];
		for(int i=0; i<m.length; i++) {
			m[i]=new MyThread();
			m[i].start();
		}
		for(int i=0; i<m.length; i++)
			m[i].join();
		System.out.println(Thread.currentThread().getName()+" "+ Thread.currentThread().getState());
		System.out.println(var1+ " "+var2);
	}
	
	
	static class MyThread extends Thread{
		public void run() {
			try {
				mutex.acquire();
				var1=var1+1;
				mutex.release();
				var2=var2+1;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}	
