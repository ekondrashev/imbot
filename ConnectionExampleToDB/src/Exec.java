import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			if (!map.containsKey("user_id") || !map.containsKey("message")) {
				map.clear();
				map.put("HELP", USAGE);
			}
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

	// --cmd=send_message --user_id=4897 --message=Hello
	public static void main(String args[]) throws Exception {
		Boolean chek = false;
		String sendLine = "";

		Map<String, String> map = parsingArgs(args);
		JDBCCommandsDAO jdbcCommandsDAO = new JDBCCommandsDAO();
		jdbcCommandsDAO.establishConnection();

		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (entry.getKey().equals("cmd")) {
	            Logger.getLogger(Exec.class.getName()).log(Level.INFO, "Our command is cmd");
				if (jdbcCommandsDAO.isCommandExist(entry.getValue())) {
		            Logger.getLogger(Exec.class.getName()).log(Level.INFO, "Command is exit");
					if (entry.getKey().equals("cmd")
							&& entry.getValue().equals("start")) {
						// IT School
						// ("Java -cp "C:\Users\IT
						// School\Galina\Galina\Messagelistener\bin";"C:\Users\IT
						// School\Galina\libs\*" Main")
						// String
						// cmd=("Java -cp \"C:\\Users\\IT School\\Galina\\Galina\\Messagelistener\\bin\";\"C:\\Users\\IT School\\Galina\\libs\\*\" Main");

						// Home
						// ("Java -cp "D:\Galina\NewProject\imbot\Messagelistener\bin";"D:\Galina\NewProject\libs\*" Main")
						String cmd = ("Java -cp \"D:\\Galina\\NewProject\\imbot\\Messagelistener\\bin\";\"C:\\Galina\\NewProject\\libs\\*\" Main");
						executeCmd(cmd);
						return;
					} else if ((entry.getKey().equals("cmd") && entry
							.getValue().equals("send_message"))
							|| entry.getKey().equals("user_id")
							|| entry.getKey().equals("message")) {
						chek = true;
						sendLine = sendLine + " " + "--" + entry.toString();
					} else if (entry.getKey().equals("cmd")
							&& entry.getValue().equals("stop")) {
						Send.sendMessage("--" + entry.toString());
						break;
					}
				}
				else System.out.println("This command is not exist");
			}
		}
		if (chek)
			Send.sendMessage(sendLine);

	}

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

}
