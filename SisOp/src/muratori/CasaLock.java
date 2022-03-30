package muratori;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.*;
public class CasaLock extends Casa{
	
	private Lock l=new ReentrantLock();
	//private Condition inizio=l.newCondition();
	private Condition turnoM=l.newCondition();
	private Condition turnoC=l.newCondition();
	private boolean turnoMattone=false;
	private boolean turnoCemento=false;
	private boolean prima=true;
	private LinkedList<Thread>[] turno=new LinkedList[2];
	/*private LinkedList<Thread> codaMattone=new LinkedList<>();
	private LinkedList<Thread> lavorando=new LinkedList<>();*/
	
	public CasaLock(int p, int file) {
		super(p, file);
		turno[MATTONE]=new LinkedList<>();
		turno[CEMENTO]=new LinkedList<>();
	}
	
	@Override
	protected boolean inizia(int t) throws InterruptedException {
		l.lock();
		try {
			turno[t].addLast(Thread.currentThread());
			//codaMattone.add(Thread.currentThread());
			//System.out.println(toString());
			while(!turnoCemento(t)) {
				turnoC.await();
			}
			while(!turnoMattone(t)) {
				turnoM.await();
			}
			/*while(!possoIniziare(t)) {
				inizio.await();
			}*/
		} finally {
			l.unlock();
		}
		return paretiFatte<pareti;
	}

	@Override
	protected void termina(int t) throws InterruptedException {
		l.lock();
		try {
			turno[t].remove();
			lavora(t);
			TimeUnit.SECONDS.sleep(1);
			//System.out.println(toString());
			if(turnoMattone)
				turnoM.signal();
			else if(turnoCemento)
				turnoC.signal();
			//inizio.signalAll();
		} finally {
			l.unlock();
		}
		
	}
	
	private boolean turnoMattone(int t) {
		if(t==CEMENTO)
			return true;
		return turnoMattone && Thread.currentThread()==turno[t].getFirst();
	}
	
	private boolean turnoCemento(int t) {
		if(t==MATTONE)
			return true;
		if(prima) {
			prima=false;
			return true;
		}
		return turnoCemento && Thread.currentThread()==turno[t].getFirst();
	}
	
	private boolean preparato(int t) {
		if(t==CEMENTO)
			return turnoCemento;
		return turnoMattone;
	}
	
	private boolean possoIniziare(int t) {
		return Thread.currentThread()==turno[t].getFirst();
	}
	
	private void lavora(int t) {
		System.out.format("Muratore %d mette una fila di %s %n", Thread.currentThread().getId(), 
				t==MATTONE? "mattoni":"cemento");
		if(t==CEMENTO) {
			turnoMattone=true;
			turnoCemento=false;
		}
		else if(t==MATTONE) {
			fileAttuali++;
			System.out.println(fileAttuali);
			turnoCemento=true;
			turnoMattone=false;
		}
		if(fileAttuali==filePerMuro) {
			paretiFatte++;
			System.out.println("PARETE COMPLETATA!");
			fileAttuali=0;
			if(paretiFatte==pareti) {
				System.out.println("CASA COMPLETATA");
				System.exit(0);
			}
		}
		
	}

	
	public String toString() {
		StringBuilder st=new StringBuilder(150);
		/*st.append("turno mattone?: "+turnoMattone+ "\n");
		st.append("turno cemento?: "+turnoCemento+ "\n");*/
		st.append("fila mattone: [");
		for(Thread t:turno[MATTONE])
			st.append(" "+t.getId());
		st.append(" ]\n");
		st.append("fila cemento: [");
		for(Thread t:turno[CEMENTO])
			st.append(" "+t.getId());
		st.append(" ]\n");
		return st.toString();
	}
	
	public static void main(String[] args) {
		CasaLock ca=new CasaLock(2,20);
		ca.test(5, 7);
	}

}
