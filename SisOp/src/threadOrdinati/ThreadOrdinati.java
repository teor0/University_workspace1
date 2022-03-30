package threadOrdinati;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
public class ThreadOrdinati {

	public static int N=20;
	public static Semaphore[] mutex=new Semaphore[N];
	private static class Atleta extends Thread{
		private int numMaglia;
		public Atleta(int nMaglia) {
			this.numMaglia=nMaglia;
		}
		
		public void run() {
			try {
				mutex[numMaglia].acquire();
				System.out.println(numMaglia);
				TimeUnit.SECONDS.sleep(1);
				if(numMaglia<N-1)
					mutex[numMaglia+1].release();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		mutex[0]=new Semaphore(1);
		for(int i=1; i<N; i++)
			mutex[i]=new Semaphore(0);
		Atleta[] atleti= new Atleta[N];
		for(int i=0; i<N; i++){
			atleti[i]=new Atleta(i);
			atleti[i].start();
		}
		
	}
	
}
