package esempi;

public class OccorrenzeXY extends Thread {
	
	private int countX=0;
	private int countY=0;
	private int x;
	private int y;
	private int[] riga;
	private int lengthRiga;
	
	public OccorrenzeXY(int x, int y, int l, int[] riga){
		this.x=x;
		this.y=y;
		this.lengthRiga=l;
		this.riga=riga;
	}
	
	public void run(){
		for(int i=0; i<lengthRiga; i++) {
			if(riga[i]==x)
				countX++;
			if(riga[i]==y)
				countY++;
		}
	}
	
	public int getX(){
		try {
			this.join();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		return countX;
	}
	
	public int getY(){
		try {
			this.join();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		return countY;
	}
	
	
}
