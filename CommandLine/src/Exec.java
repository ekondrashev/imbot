import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exec implements Parsing{

	private static final String USAGE = "Usage: Exec --cmd=<os_cmd> -param1=<value> -param2=<value>";

	

	
	
	public static Map<String, String> decodeToMapString(String args[]) {
		Map<String, String> ourResult = new LinkedHashMap<>();

		String cmdSignature = "--cmd=";
		String cmdHelp = "--help";
		String key = null, value = null;
		String errorkey = "Error";
		String errorvalue = "Error input command!!!";

	

			// первый проход
			if (args.length > 0) {
				if (args[0].startsWith(cmdSignature)) {
					value = args[0].substring(cmdSignature.length(),
							args[0].length());
					key = "command";
					ourResult.put(key, value);
				}

				else if (args[0].startsWith(cmdHelp)) {
					value = USAGE;
					key = "help";
					ourResult.put(key, value);
					return ourResult;
				} else {
					ourResult.put(errorkey, errorvalue);
					return ourResult;
				}

			}

			// для остального ищем первый минус и потом равно
			for (int i = 1; i <= args.length - 1; i++) {
				String arg = args[i];

				int posMinus = arg.indexOf("-");
				int posEquals = arg.indexOf("=");

				if (posMinus == -1 || posEquals == -1) {
					ourResult.put(errorkey, errorvalue);
					break;
				}

				key = arg.substring(posMinus + 1, posEquals);
				value = arg.substring(posEquals + 1, arg.length());
				ourResult.put(key, value);
			}
			return ourResult;
		}
	

	public static void main(String args[]) {

		Map<String, String> ourResult = decodeToMapString(args);

		if (ourResult.size() == 0) {
			System.out.println(USAGE);

		} else if (ourResult.containsKey("Error")) {

			System.out.println(ourResult.get("Error"));
		} else {
			for (Entry<String, String> elem : ourResult.entrySet()) {

				System.out.println(elem.getKey() + ":" + elem.getValue());
			}
		}

	}

	@SuppressWarnings("unused")
	private static void executeCmd(String cmd) throws InterruptedException {
		Runtime runtime = Runtime.getRuntime();

		try {
			Process process = runtime
					.exec(new String[] { "cmd.exe", "/c", cmd });
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


	@Override
	public Map<String, String> decodeToMap(String[] args) {
		return decodeToMapString(args);
		 
	}


	
}