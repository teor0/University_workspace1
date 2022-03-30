package cronometro;

public class CronometroDaemon {
	
	public static void main(String[] args) {
		Cronometro cronometro = new Cronometro();
		cronometro.setDaemon(true);
		cronometro.start();
		
		System.out.println("Fine");
	}
}
