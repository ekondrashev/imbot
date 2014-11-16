
public class BotYura {
	
	//Bot —cmd=send_message -user_id=<id> -message=<message>
	
	private static final String USAGE = "Usage: BotYura -cmd=send_message -user_id=<id> -message=<message>";
	private static final String[] botCmd = {"send_message", "-user_id=", "-message="};

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String cmd = null;
		String userId = null;
		String userMessage = null;

		String cmdSignature = "-cmd=";

		for (String arg : args) {
			if (arg.startsWith(cmdSignature)) {
				cmd = arg.substring(cmdSignature.length(), arg.length());
				if(cmd.equals(botCmd[0]) != true){
					System.out.println("Bad command!!!");
					cmd = null;
					break;
				}
				continue;
			}
			if(arg.startsWith(botCmd[1])){
				userId = arg.substring(botCmd[1].length(), arg.length());
				continue;
			}
			if(arg.startsWith(botCmd[2])){
				userMessage = arg.substring(botCmd[2].length(), arg.length());
				continue;
			}
		}

		if (cmd != null && userId != null && userMessage != null) {
			System.out.println("Command has been used - " + cmd);
			System.out.println("User ID - " + userId);
			System.out.println("Message - " + userMessage);
		} else {
			System.out.println(USAGE);
		}


	}

}
