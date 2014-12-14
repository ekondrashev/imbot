import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

public class MySender {

	private static final Logger log = Logger.getLogger(MySender.class);
	
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

		log.debug("Initialization param queue: "+queue);
		log.debug("Initialization param host: "+host);
		
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
				log.debug("Send to run: "+path + " " + ourParam);
				executeListener(path + " " + ourParam);
			} else if (ourResult.get("command").equals("stop")) {

				snd = new Send(queue, host);
				snd.sendMessage("--cmd=stop");
				log.debug("Send message: --cmd=stop");
			}

			else {
				String ourString = "";
				for (String arg : args) {
					ourString = ourString + arg + " ";
				}

				snd = new Send(queue, host);
				snd.sendMessage(ourString);
				log.debug("Send message: "+ourString);
			}
		else if (ourResult.containsKey("Error")) {
			log.debug("Error: "+ourResult.get("Error"));
			log.debug("Help: "+ourResult.get("help"));
			System.out.println(ourResult.get("Error"));
			System.out.println(ourResult.get("help"));
		}

	}

	private static void executeListener(String cmd) throws InterruptedException {
		log.debug("Run "+cmd);
		ProcessBuilder procBuilder = new ProcessBuilder(new String[] {
				"cmd.exe", "/c", cmd });
		procBuilder.redirectErrorStream(true);
		try {
			@SuppressWarnings("unused")
			Process process = procBuilder.start();

		} catch (Exception e) {
			System.out.println("Command execution failed");
			e.printStackTrace();
			log.error("Run not enabled "+cmd);
		}
	}
}