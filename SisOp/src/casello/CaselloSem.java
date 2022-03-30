package casello;

import java.util.concurrent.Semaphore;

public class CaselloSem extends Casello{

	private Semaphore[] portaS;
	private Semaphore mutex;
	
	public CaselloSem(int numPorte, int tariffa) {
		super(numPorte,tariffa);
		portaS = new Semaphore[numPorte];

		for (int i = 0; i < numPorte; i++) {
			portaS[i] = new Semaphore(1, true);
		}
		mutex = new Semaphore(1);

		System.out.println(this);
	}

	@Override
	public void paga(int porta, int km) throws InterruptedException {	
		mutex.acquire();
		incasso+=km*tariffa;
		portaS[porta].release();
		System.out.format("Veicolo %d ha abbandonato la porta %d pagando la tariffa di %d%n", Thread.currentThread().getId(),
				porta, km * tariffa);
		System.out.println(this);

		mutex.release();
	}

	@Override
	public void scegliPorta(int porta) throws InterruptedException {
		portaS[porta].acquire();
		System.out.format("Veicolo %d è entrato nella porta %d%n", Thread.currentThread().getId(), porta);
		System.out.println(this);
	}
	
	public String toString() {
		String ret = "Incasso del casello: " + incasso;
		return ret;
	}
	
	public static void main(String[] args) {
		int N = 5;
		int T = 10;
		int V = 10;

		Casello casello = new CaselloSem(N, T);
		casello.test(V);
	}
	
}
