package prove3;

public class Prova3Esonero {

	static MyThread4 ta,tb,tc,td;
	
	public static void main(String[] args) throws InterruptedException{
		ta=new MyThread4("Th-A", new MyThread4[] {});
		tb=new MyThread4("Th-B", new MyThread4[] {ta});
		tc=new MyThread4("Th-C", new MyThread4[] {tb});
		td=new MyThread4("Th-D", new MyThread4[] {tc});
		ta.start(); tb.start(); tc.start(); td.start();
	}
	
	
	static class MyThread4 extends Thread{
		private Thread[] tArray;
		
		public MyThread4(String n, MyThread4[] r) {
			setName(n);
			this.tArray=r;
		}
		
		public void run() {
			try {
				for(Thread th:tArray)
					th.join();
				System.out.println(getName()+ " "+ ta.getState());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
