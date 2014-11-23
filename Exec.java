import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ITschool3 on 12.11.2014.
 */
public class Exec {

	private static final String USAGE = "Usage: Exec --cmd=<os_cmd>";

	// --param=pamparam --dasdsa das das dsa --cmd=dir --finalargument --cmd=ver
	/*
	 * [ "--param=pamparam", "--dasdsa", "das", "das", "dsa", "--cmd=dir",
	 * "--finalargument", "--cmd=ver"]
	 */

	// ["--cmd=asd", "-c", "hostname"]
	public static void main(String args[]) {
		String cmd = null;

		String cmdSignature = "--cmd=";

		for (String arg : args) {
			if (arg.startsWith(cmdSignature)) {
				cmd = arg.substring(cmdSignature.length(), arg.length());
				try {
					executeCmd(cmd);
				} catch (InterruptedException e) {

				}
			}
		}

		if (cmd != null) {
			System.out.println(cmd);
		} else {
			System.out.println(USAGE);
		}

		// method1();

	}

	private static void executeCmd(String cmd) throws InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		
		try {
			Process process = runtime.exec(new String[] { "cmd.exe", "/c",  cmd });
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