package contocorrente;

import java.util.concurrent.atomic.AtomicInteger;

public class ContoCorrenteAI extends ContoCorrente{
	
	protected AtomicInteger deposito;

	public ContoCorrenteAI(int depositoIniziale) {
		super(depositoIniziale);
		deposito = new AtomicInteger(depositoIniziale);
	}

	public void deposita(int importo) {
		deposito.addAndGet(importo);
	}

	public void ritira(int importo) {
		deposito.addAndGet(-importo);
	}

	public int getDeposito() {
		return deposito.get();
	}

	
	
}
