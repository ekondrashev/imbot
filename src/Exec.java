
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Exec {

	public static final String USAGE = "Usage: Exec --cmd=send_message --user_id=<id> --message=<message>";
	public static boolean check;
	private static final String regexpHelp = "\\-\\-(help|Help|HELP)\\=?";
	private static final String regexpCMD = "--(cmd)=(.+)$";
	public static final String pat0 = "--(.+)=(.+)$";
	public static final String pat1 = "--(.+)=";
	static Pattern p1 = Pattern.compile(pat1);
	public static final String pat2 = "=(.+)$";
	static Pattern p2 = Pattern.compile(pat2);
	
	public static Map<String, String> parsingArgs(String[] args) {
		    Map<String, String> map = new LinkedHashMap<>();
		
			if (args.length>0) {
			for (String arg : args) {
				if (checkWithRegExp(regexpCMD, arg)) {
					check = true;
					map.put("cmd", retFindsVal(p2,arg));}
					else if (checkWithRegExp(pat0, arg)) {
						map.put(retFindsVal(p1,arg), retFindsVal(p2,arg));}
						else if (checkWithRegExp(regexpHelp, arg)) {
							check = false;
						} 			
						else  System.out.println("You can use only this commands: "+"'"+USAGE+"'");
				}
			return map;
		}
            else check = false;
			return map;
	
	}
	
	public static boolean checkWithRegExp(String regexpString, String arg)
    {  
        Pattern p = Pattern.compile(regexpString);  
        Matcher m = p.matcher(arg);  
        return m.matches();  
    }
	
	public static String retFindsVal(Pattern p, String arg) {
	    Matcher m = p.matcher(arg);  
	    if(m.find())	return m.group().replaceAll("=", "").replaceAll("-", ""); 
	    else return	"";
	    }	
	
	public static void main(String args[]) throws InterruptedException {
		
        Map<String, String> map = parsingArgs(args);
       	     
	    for (Map.Entry<String, String> entry : map.entrySet()) {
	    	try {
	    		executeCmd(entry.getValue());
				System.out.println(entry.getValue());
			} catch (InterruptedException e) {

			}
      }
	     
	  
		if (check) {
			System.out.println("It is correct!");
		} else {
			System.out.println(USAGE);
		}

		    
	}

	




	private static void executeCmd(String map) throws InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		
		try {                                              
			Process process = runtime.exec(new String[] { "cmd.exe", "/c",  map });
			process.waitFor();
			BufferedReader bReader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = "";
			while ((line = bReader.readLine()) != null) {
				System.out.println(line);
			}
			bReader.close();
		} catch (IOException e) {
			System.out.println("Command execution failed");
			e.printStackTrace();
		}
	}

	
}
