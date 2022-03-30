package esercizi;

public class Multipli3e5 {
	
	public static int multipli() {
		int ret=0;
		for(int i=1; i<1000; i++)
			if(i%3==0 || i%5==0)
				ret+=i;
		return ret;
	}

	
}
