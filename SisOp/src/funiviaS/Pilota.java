package funiviaS;

import java.util.concurrent.TimeUnit;

public class Pilota extends Thread{

	private Funivia f;
	
	
	public Pilota(Funivia fu){
		f=fu;
	}
	
	public void run(){
		while(true) {
			try {
				attesa(4);
				f.pilotaStart();
				f.pilotaEnd();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
	
	private void attesa(int operazione) throws InterruptedException {
		TimeUnit.SECONDS.sleep(operazione);
	}
	
	
}
