package poo.regex;
import java.util.regex.*;
public class RegOp {
	
	public static double risultato(String s) {
		String piu="\\d+\\+\\d+";
		String meno="\\d+\\-\\d+";
		String per="\\d+\\*\\d+";
		String div="\\d+\\/\\d+";
		double ris=0d;
		Pattern pp=Pattern.compile(piu);
		Matcher mp=pp.matcher(s);
		Pattern pm=Pattern.compile(meno);
		Matcher mm=pm.matcher(s);
		Pattern pper=Pattern.compile(per);
		Matcher mper=pper.matcher(s);
		Pattern pd=Pattern.compile(div);
		Matcher md=pd.matcher(s);
		if(mp.matches()) {
			int i=s.indexOf("+");
			String primoNum=s.substring(0,i);
			String secondoNum=s.substring(i+1);
			double pn= Double.parseDouble(primoNum);
			double sn= Double.parseDouble(secondoNum);
			ris=pn+sn;
		}
		else if(mm.matches()) {
			int i=s.indexOf("-");
			String primoNum=s.substring(0,i);
			String secondoNum=s.substring(i+1);
			double pn= Double.parseDouble(primoNum);
			double sn= Double.parseDouble(secondoNum);
			ris=pn-sn;
		}
		else if(mper.matches()) {
			int i=s.indexOf("*");
			String primoNum=s.substring(0,i);
			String secondoNum=s.substring(i+1);
			double pn= Double.parseDouble(primoNum);
			double sn= Double.parseDouble(secondoNum);
			ris=pn*sn;
		}
		else if(md.matches()) {
			int i=s.indexOf("/");
			String primoNum=s.substring(0,i);
			String secondoNum=s.substring(i+1);
			double pn= Double.parseDouble(primoNum);
			double sn= Double.parseDouble(secondoNum);
			ris=pn/sn;
		}	
		return ris;
	}

	public static void main(String[] args) {
		double r=risultato("23-5");
		System.out.println(r);
	}

}
