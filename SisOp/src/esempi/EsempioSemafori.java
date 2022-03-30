package esempi;
import java.util.Random;
import java.util.concurrent.Semaphore;
public class EsempioSemafori{
	
	private static Semaphore mutex=new Semaphore(1);
	
	private static class StampaA extends Thread{
		
		@Override
		public void run(){
			try{
				mutex.acquire();
				System.out.println("A");
				mutex.release();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	private static class StampaB extends Thread{
		
		@Override
		public void run(){
			try{
				mutex.acquire();
				System.out.println("B");
				mutex.release();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args){
		Random r=new Random();
		while(r.nextBoolean()) {
			new StampaA().start();
			new StampaB().start();
		}
	}
	
	
}