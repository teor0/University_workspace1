package firenze;

import java.util.concurrent.TimeUnit;

public class Guida extends Thread{

	private Tour t;
	
	
	public Guida(Tour t) {
		this.t=t;
	}
	
	public void run() {
		while(true) {
			try {
				t.attendiFormazioneGruppo();
				t.visitaInizia();
				t.visitaFine();
				System.out.println("riposa");
				riposa();	
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	
	private void riposa() throws InterruptedException{
		TimeUnit.SECONDS.sleep(6);
	}
	
	
}
