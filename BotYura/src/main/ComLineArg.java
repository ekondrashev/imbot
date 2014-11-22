/**
 * 
 */
package main;

/**
 * @author Yura
 *
 */
public class ComLineArg {
	
	public static final String USAGE = "Usage: BotYura -cmd=send_message -user_id=<id> -message=<\"message\">";
	public static final String cmdSignature = "-cmd=";
	public String cmd;
	public String userId;
	public String userMessage;
	
	public ComLineArg() {
		cmd = null;
		userId = null;
		userMessage = null;
	}
	
	public ComLineArg(String cmd, String userId, String userMessage){
		this.cmd = cmd;
		this.userId = userId;
		this.userMessage = userMessage;
	}
	public void printComLineArg(){
		switch(cmd){
		case "send_message":
			if(userId != null && userMessage != null){
				System.out.println("Command - "+ cmd);
				System.out.println("User - "+ userId);
				System.out.println("Text messages - "+ userMessage);
			}
			else{
				if(userId == null){
					System.out.println("You did not input a username");
				}
				if(userMessage == null){
					System.out.println("You did not write a message");
				}
				System.out.println(USAGE);
			}
			break;
		case "help":
		default:
			System.out.println(USAGE);
		}
		
	}
		

}
