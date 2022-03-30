package cinquefilosofi;
import java.util.HashSet;
import java.util.concurrent.*;
public class TavoloSem extends Tavolo{
	
	private Semaphore mutex=new Semaphore(1);
	private Semaphore[] mangio=new Semaphore[NUM_FILOSOFI];
	private HashSet<String> combo=new HashSet<>();
	private boolean filosofiTavola[]=new boolean[NUM_FILOSOFI];
	
	public TavoloSem() {
		for(int i=0;i<mangio.length; i++)
			mangio[i]=new Semaphore(1);
	}
	
	@Override
	public void prendiBacchette(int i) throws InterruptedException {
		while(bacchette[i]|| bacchette[(i+1)%NUM_FILOSOFI]) {
			if(bacchette[i])
				mangio[i].acquire();
			else
				mangio[(i+1)%NUM_FILOSOFI].acquire();
		}
		bacchette[i]=true;
		bacchette[(i+1)%NUM_FILOSOFI]=true;
		filosofiTavola[i]=true;
		aggiungiCombinazione();
		stampaCombinazioni();
	}

	@Override
	public void rilasciaBacchette(int i){
		bacchette[i] = false;
		bacchette[(i + 1) % NUM_FILOSOFI] = false;
		mangio[i].release();
		mangio[(i+1)%NUM_FILOSOFI].release();
		filosofiTavola[i]=false;
		aggiungiCombinazione();
		stampaCombinazioni();
	}
	
	private void stampaCombinazioni() {
		System.out.print("{");
		for (String s : combo) {
			System.out.print(s);
		}
		System.out.println("}");

	}
	
	private void aggiungiCombinazione() {
		String ret = "[";
		for (int i = 0; i < NUM_FILOSOFI; i++) {
			if (filosofiTavola[i])
				ret += i;
			else
				ret += "-";
		}
		ret += "]";
		combo.add(ret);
	}
	public static void main(String[] args) {
		TavoloSem tavolo=new TavoloSem();
		tavolo.test();
	}
}
