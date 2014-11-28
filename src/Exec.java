
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Exec {

	public static final String USAGE = "Usage: Exec --cmd=send_message --user_id=<id> --message=<message>";
	private static final String regexpCMD = "--(cmd)=(.+)$";
	public static final String pat = "--(.+)=(.+)$";
	static Pattern p = Pattern.compile(pat);
	
	
	public static Map<String, String> parsingArgs(String[] args) {
		    Map<String, String> map = new LinkedHashMap<>();
		    boolean check = false;
		    
			if (args.length>0) {
				if (checkWithRegExp(regexpCMD, args[0]))  {
					check = true;	
			        for (String arg : args) {
				      map.put(retFindsVal(p,arg, 1), retFindsVal(p,arg, 2));
				      }		
			    }
			}
			if (! check) map.put("HELP", USAGE);
			return map;
	}
	
	public static boolean checkWithRegExp(String regexpString, String arg)  {  
        Pattern p = Pattern.compile(regexpString);  
        Matcher m = p.matcher(arg);  
        return m.matches();  
    }
	
	public static String retFindsVal(Pattern p, String arg, int numGroup) {
	    Matcher m = p.matcher(arg);  
	    if(m.find())	return m.group(numGroup).replaceAll("=", "").replaceAll("-", ""); 
	    else return	"";
	    }	
	
	public static void main(String args[]) throws InterruptedException {
		
        Map<String, String> map = parsingArgs(args);
       	     
	    for (Map.Entry<String, String> entry : map.entrySet()) {
	    	try {
	    		executeCmd(entry.getValue());
				System.out.println(entry.getValue());
			} catch (InterruptedException e) {}
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
