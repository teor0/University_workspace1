package poo.geometria;
public class Geometria{


	public static void main(String [] args) {
		Punto p1= new Punto(4,4);
		Punto p2= new Punto(3,3);
		Punto p3= new Punto(3,5);
		Disco d= new Disco(p1, 4);
		Disco d2= new Disco(p2, 10);
		System.out.println(d);
		System.out.println(d2);
		System.out.println(" ");
		System.out.println(p3);
		p3=d2;
		System.out.println(" ");
		System.out.println(p3);
	}
}

	

	