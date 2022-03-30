package esercizi;
import java.io.*;
public class Triplette {
	public static void calc(String da, String fino) {
		System.out.print("M = ");
		int m = Math.abs(Integer.parseInt(da));
		System.out.print("N = ");
		int n = Math.abs(Integer.parseInt(fino));
		for(int a = m; a <= n; a++)
			for(int b = a + 1; b <= n; b++)
				for(int c = b + 1; c <= n; c++)
					if(c * c == a * a + b * b) {
						System.out.println(a + ", " + b + ", " + c);
						if(a+b+c==1000)
							return;
		}
	}
}	
