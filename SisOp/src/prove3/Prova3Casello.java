package prove3;

import java.util.concurrent.TimeUnit;

public class Prova3Casello {
	
	public static void main(String[] args) throws InterruptedException {
		MyThread[] m=new MyThread[6];
		for(int i=0; i<m.length; i++)
			m[i]=new MyThread(i,m);
		for(int j=0; j<m.length; j++)
			m[j].start();
		for(int i=0; i<m.length; i++)
			System.out.println(m[i].getState());
	}
	
}

 class MyThread extends Thread{
		
		private int id; private MyThread[] threads;
		
		public MyThread(int id, MyThread[] t) {
			this.id=id;
			threads=t;
		}
		
		public void run() {
			try {
				int s=id+1;
				if(s<threads.length) 
					threads[s].join();
				TimeUnit.SECONDS.sleep(id);
				System.out.println("T "+id+ " "+ getState() );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

