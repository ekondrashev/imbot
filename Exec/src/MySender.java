import java.util.Map;
import java.util.Map.Entry;

public class MySender {

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {

		// Send.sendMessage("--cmd=print -userid=vasia -sendmessage=hello");
		// Send.sendMessage("--cmd=stop");
		// Send.sendMessage("--cmd=ask -text=what are you doing?");

		String host = "localhost";
		String queue = "QUEUE";
		Send snd;

		ParsingRegulary exec = new ParsingRegulary();
		Map<String, String> ourResult = exec.decodeToMap(args);

		if (ourResult.containsKey("host"))
			host = ourResult.get("host");

		if (ourResult.containsKey("queue"))
			queue = ourResult.get("queue");

		if (ourResult.containsKey("command"))
			if (ourResult.get("command").equals("start")
					&& ourResult.containsKey("path")) {
				String path = ourResult.get("path");
				String ourParam = "--cmd=start ";
				for (Entry<String, String> elem : ourResult.entrySet()) {
					if (elem.getValue().equals("start")
							|| elem.getKey().equals("path"))
						continue;
					ourParam = ourParam + "-" + elem.getKey() + "="
							+ elem.getValue() + " ";
				}
				executeListener(path + " " + ourParam);
			} else if (ourResult.get("command").equals("stop")) {

				snd = new Send(queue, host);
				snd.sendMessage("--cmd=stop");
			}

			else {
				String ourString = "";
				for (String arg : args) {
					ourString = ourString + arg + " ";
				}

				snd = new Send(queue, host);
				snd.sendMessage(ourString);

			}
		else if (ourResult.containsKey("Error")) {
			System.out.println(ourResult.get("Error"));
			System.out.println(ourResult.get("help"));
		}

	}

	private static void executeListener(String cmd) throws InterruptedException {
		ProcessBuilder procBuilder = new ProcessBuilder(new String[] {
				"cmd.exe", "/c", cmd });
		procBuilder.redirectErrorStream(true);
		try {
			@SuppressWarnings("unused")
			Process process = procBuilder.start();

		} catch (Exception e) {
			System.out.println("Command execution failed");
			e.printStackTrace();
		}
	}
}