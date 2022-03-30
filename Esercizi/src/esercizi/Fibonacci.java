package esercizi;

public class Fibonacci {
	
	public static int fib(int limite) {
		return fib(0,1,limite);
	}
	
	private static int fib(int pre,int next,int limite) {
		int tmp=next;
		next=pre+next;
		pre=tmp;
		if(next>=limite)
			return tmp;
		System.out.println(next);
		return fib(pre,next,limite);
	}
	
	public static int fibpari(int lim){
		return fibpari(0,1,lim,0);
	}
	
	private static int fibpari(int pre, int next, int lim, int ret){
		int tmp=next;
		next=pre+next;
		pre=tmp;
		if(next>=lim)
			return tmp;
		if(next%2==0)
			ret+=next;
		System.out.println(ret);
		return fibpari(pre,next,lim,ret);
	}
	
}
