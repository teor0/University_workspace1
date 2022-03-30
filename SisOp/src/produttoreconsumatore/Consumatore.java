package produttoreconsumatore;
import java.util.concurrent.*;
public class Consumatore implements Runnable {

	  private Buffer buffer;
	  

	  public Consumatore(Buffer b) { buffer = b; }

	  public void run() {
	    try {
	      while (true) {
	        Elemento el = buffer.get();
	        consuma(el);
	      }
	    } catch (InterruptedException e) {}
	  }

	  private void consuma(Elemento el) throws InterruptedException {
		System.out.format("Il thread %d consuma l'elemento %s %n", Thread.currentThread().getId(), el);
	    TimeUnit.SECONDS.sleep(el.getI());
	  }
}

