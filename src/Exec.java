import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ITschool3 on 12.11.2014.
 */
public class Exec {

	private static final String USAGE = "Usage: Exec --cmd=<send_message>";

	//—cmd=send_message -user_id <id> -message <message>
	public static void main(String args[]) throws InterruptedException {
		boolean check = false;

		String cmdSignature = "—cmd="; //here '--' was somehow replaced with '—', should be taken into account
		String cmdSignature2 = "-user_id=";
		String cmdSignature3 = "-message=";
		String cmd;
		
		Map<String, String> map = new HashMap<>();

	     
		for (String arg : args) {
			System.out.println(arg);
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
			}
		
			     
	    for (Map.Entry<String, String> entry : map.entrySet()) {
	    	try {
	    		executeCmd(entry.getValue());
				System.out.println(entry.getValue());
			} catch (InterruptedException e) {

			}
	    	
	                   
	    }
	     
	    //System.out.println();

		
		
		

		if (check) {
			System.out.println("OK!");
		} else {
			System.out.println(USAGE);
		}

		// method1();
	    
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

	private static void method1() {
		throw new NullPointerException();
	}
}
