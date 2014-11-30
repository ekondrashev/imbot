import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Exec1 {
	public static String cmd = "command";
	public static Pattern patSignature;
	public static Matcher match;
	public static String error = "command error";
	//public static String empty = "command is empty";
	public static  String USAGE = "Usage: Exec --cmd=<os_cmd> -param1=<value> -param2=<value>";
	public static	String [] patt = { "(^--[Cc][Mm][Dd]=)(.+$)" ,  "(^--[Hh][Ee][Ll][Pp]$)" , "^=[a-z]+$" };
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int y = 0, i = 0; i <= 2 && y <= args.length - 1; i++) {
		
		if(Check(args[y], patt[i])) { if(i==0){
			System.out.println(cmd+": "+match.group(2));
		} if(i==1){
			System.out.println(cmd+": "+match.group());
			System.out.println(USAGE);
			
		}y++;

}else System.out.println(error);
		}
		
		
		
	}
	public static boolean Check(String args,String patt) {
		
		patSignature= Pattern.compile(patt);
		match = patSignature.matcher(args);
		return match.matches();
	
	 
	}
	
}
