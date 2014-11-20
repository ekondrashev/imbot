
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;



public class Exec {

	private static final String USAGE = "Usage: Exec --cmd=send_message --user_id=<id> --message=<message>";
	private static boolean check = false;
	
	
	public static Map<String, String> parsingArgs(String[] args) {
		    Map<String, String> map = new LinkedHashMap<>();
			String cmdSignature = "--cmd="; 
			String cmdSignature2 = "--user_id=";
			String cmdSignature3 = "--message=";
			String cmd;
			
			for (String arg : args) {
				//System.out.println(arg);
				if (arg.startsWith(cmdSignature)) {
					check = true;
					cmd = arg.substring(cmdSignature.length(), arg.length());
					map.put(cmdSignature, cmd);}
					else if (arg.startsWith(cmdSignature2)) {
						cmd = arg.substring(cmdSignature2.length(), arg.length());
						map.put(cmdSignature2, cmd);}
						else if (arg.startsWith(cmdSignature3)) {
							cmd = arg.substring(cmdSignature3.length(), arg.length());	
							map.put(cmdSignature3, cmd);}
						else  System.out.println("You can use only this commands: "+"'"+USAGE+"'");
								
						
				}
			
			//System.out.println(map);
			return map;
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
