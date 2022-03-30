package esempi;

public class TestStampante{
	
	public static void main(String args[]){
		Stampante s1=new Stampante(1,10);
		Stampante s2=new Stampante(11,20);
		s1.print();
		s2.print();
		System.out.println("End");
	}//main	
}