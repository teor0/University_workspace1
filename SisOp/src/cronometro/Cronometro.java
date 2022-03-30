package cronometro;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Cronometro extends Thread{
	
	public void run() {
		int numSecondi=1;
		while(!isInterrupted()) {
			try {
				TimeUnit.SECONDS.sleep(1);
				//sleep(1000);
			}catch(InterruptedException e) {
				break;
			}
			System.out.println("\n" + numSecondi);
			numSecondi++;
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Cronometro cronometro = new Cronometro();

		System.out.println("Premi invio per cominciare");
		in.nextLine();
		cronometro.start();
		System.out.println("Premi invio per terminare");
		in.nextLine();
		cronometro.interrupt();
		System.out.println("Main terminato");
		in.close();
	}
}
