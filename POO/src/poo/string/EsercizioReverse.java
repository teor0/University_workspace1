package poo.string;

public class EsercizioReverse {
	
	public EsercizioReverse(){}
	
	public static String reverse(String s) {
		return reverse(s, s.length()-1, new StringBuilder(100));
	}
	
	private static String reverse(String s, int i, StringBuilder ret){
		if(i==0) {
			ret.append(s.charAt(i));
			return ret.toString() ;
		}
		ret.append(s.charAt(i));
		return reverse(s, i-1, ret);
	}
	
	public static void main(String[] args) {
		String s="madam bucchin";
		System.out.println(reverse(s));
	}
	
}
