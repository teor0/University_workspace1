package prove3;

import java.util.concurrent.TimeUnit;

public class Prova3Visualizzatore {

	public static void main(String[] args) {
		MyThread2[] m=new MyThread2[18];
		for(int i=0; i<m.length; i++)
			m[i]=new MyThread2(i,m);
		for(int j=0; j<m.length; j++)
			m[j].start();
	}	
}

 class MyThread2 extends Thread{
	
	private int id; private MyThread2[] threads;
	
	public MyThread2(int id, MyThread2[] t) {
		this.id=id;
		threads=t;
	}
	
	public void run() {
		try {
			if(id%2==0) {
				int p=id-2;
				if(p>=0)
					threads[p].join();
				TimeUnit.SECONDS.sleep(2);
				System.out.println("T "+id+ " "+ getState() );
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
