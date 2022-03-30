package esercizi;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexHTML {

	public static void main(String[] args) {
		String openTag="<(.+)>";
		String closeTag="</\\1>";
		String content="[^<]+";
		//lo \\1 si riferisce al contenuto del gruppo uno ovvero quello della openTag	         
		String regex="<(.+)>([^<]+)</\\1>";
		Pattern p=Pattern.compile(regex);
		Scanner sc=new Scanner(System.in);
		for(;;){	
			String s=sc.nextLine();
			Matcher m=p.matcher(s);
			if(m.find())
				System.out.println("ok");
			else
				System.out.println("not ok");
		}
	}

}
