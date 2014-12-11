import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

public class Exec {

	public static final String USAGE = "Usage: Exec --cmd=send_message --user_id=<id> --message=<message>";
	public static final String pat = "--(.+)=(.+)$";
	static Pattern p = Pattern.compile(pat);

	public static Map<String, String> parsingArgs(String[] args) {
		Map<String, String> map = new LinkedHashMap<>();

		if (args.length > 0) {

			for (String arg : args) {
				if (checkWithRegExp(p, arg)) {
					map.put(retFindsVal(p, arg, 1), retFindsVal(p, arg, 2));
				}
			}
		}

		if (map.containsKey("cmd") && map.containsValue("send_message")) {
			if (!map.containsKey("user_id") || !map.containsKey("message")){
				map.clear();
			map.put("HELP", USAGE);}
		}

		else if (!map.containsKey("cmd") || map.containsKey("help")) {
			map.clear();
			map.put("HELP", USAGE);
		}
		return map;
	}

	public static boolean checkWithRegExp(Pattern p, String arg) {
		Matcher m = p.matcher(arg);
		return m.matches();
	}

	public static String retFindsVal(Pattern p, String arg, int numGroup) {
		Matcher m = p.matcher(arg);
		if (m.find())
			return m.group(numGroup).toLowerCase();
		else
			return "";
	}

	public static void main(String args[]) throws Exception {
		Boolean chek=false;
		String sendLine="";
		Map<String, String> map = parsingArgs(args);
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (entry.getKey().equals("cmd") && entry.getValue().equals("start")) {
			//String cmd=("Java -cp .;\"C:\\Users\\IT School\\Galina\\commons-io-1.2.jar\";\"C:\\Users\\IT School\\rabbitmq-client.jar\" Main");

			//--cmd=send_message --user_id=4897 --message=Hello
			// Java -cp .;"C:\Users\Потемкина Галина\commons-io-1.2.jar";"C:\Users\Потемкина Галина\rabbitmq-client.jar" Main") //это сразу в cmd и все работает
			//Java -cp .;"C:\Users\Потемкина Галина\commons-io-1.2.jar";"C:\Users\Потемкина Галина\rabbitmq-client.jar" "C:\Users\Потемкина Галина\WorkProject\imbot\Messagelistener\bin\Main" //так в cmd не работает
			
			//String cmd=("Java -cp \"C:\\Users\\Потемкина Галина\\WorkProject\\imbot\\Messagelistener\\bin\";\"C:\\Users\\Потемкина Галина\\commons-io-1.2.jar\";\"C:\\Users\\Потемкина Галина\\rabbitmq-client.jar\" Main\"");
			//String cmd=("Java -cp \"C:\\Users\\IT School\\Galina\\Galina\\imbot\\Messagelistener\\bin\";\"C:\\Users\\IT School\\Galina\\commons-io-1.2.jar\";\"C:\\Users\\Galina\\rabbitmq-client.jar\" Main");
			// Java -cp "C:\Users\IT School\Galina\Galina\imbot\Messagelistener\bin";"C:\\Users\IT School\Galina\commons-io-1.2.jar";"C:\Users\Galina\rabbitmq-client.jar" Main

			//("Java -cp "C:\Users\IT School\Galina\Galina\Messagelistener\bin";"C:\Users\IT School\Galina\libs\*" Main")
			String cmd=("Java -cp \"C:\\Users\\IT School\\Galina\\Galina\\Messagelistener\\bin\";\"C:\\Users\\IT School\\Galina\\libs\\*\" Main");			
			executeCmd(cmd);
			return;
			}
			else if ((entry.getKey().equals("cmd") && entry.getValue().equals("send_message"))||
					entry.getKey().equals("user_id") ||
					entry.getKey().equals("message")) {  chek=true; 
				sendLine = sendLine + " " +"--" +entry.toString();
				 }
			else if (entry.getKey().equals("cmd") && entry.getValue().equals("stop")){
				Send.sendMessage("--" +entry.toString());
				break;}
		}
		if (chek) Send.sendMessage(sendLine);

	}
	

	
	private static void executeCmd(String cmd) throws InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		
		try {
			Process process = runtime.exec(new String[] { "cmd.exe", "/c", cmd });
//			process.waitFor();
//			BufferedReader bReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//			String line = "";
//			while ((line = bReader.readLine()) != null) {
//				System.out.println(line);
//			}
//			bReader.close();
		} catch (IOException e) {
			System.out.println("Command execution failed");
			e.printStackTrace();
		}
	}
	
	
}
