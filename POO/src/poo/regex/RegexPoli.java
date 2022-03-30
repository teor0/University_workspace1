package poo.regex;
import java.util.regex.*;
import poo.polinomi.*;
public class RegexPoli {
	public static Polinomio validazione(String s) {
		//            1     2        3        4       5   6       7  8            9
		String regeM="((\\-?\\d+(?=[xX]))?(\\-?[xX])(\\^(\\d+))?)|((\\-?)[xX])|(\\-?\\d+)";
		String regeP="[(((\\-\\+)?\\d+(?=[xX]))?(\\-?[xX])(\\^(\\d+))?)|((\\-?)[xX])|(\\-?\\d+)]+";
		Polinomio p=new PolinomioLL();
		Pattern paP=Pattern.compile(regeP);
		Matcher maP=paP.matcher(s);
		Pattern paM=Pattern.compile(regeM);
		Matcher maM=paM.matcher(s);
		if(!maP.matches())
			throw new IllegalArgumentException("Polinomio impossibile");
		while(maM.find()) {
			int coef=0;
			int grado=0;
			if(maM.group(3)!=null && maM.group(2)!=null) {
				if(maM.group(5)!=null)
					grado=Integer.parseInt(maM.group(5));
				else 
					grado=1;
				coef=Integer.parseInt(maM.group(2));
			}
			else {
				if(maM.group(2)==null&& maM.group(5)==null) {
					coef=1;
					grado=1;
				}
				coef=Integer.parseInt(maM.group());
			}
			p.add(new Monomio(coef,grado));
		}
		return p;
	}
}
