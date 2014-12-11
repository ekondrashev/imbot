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
	public String errorMessage;
	
	public ComLineArg() {
		cmd = null;
		userId = null;
		userMessage = null;
		errorMessage = null;
	}
	
	public ComLineArg(String cmd, String userId, String userMessage){
		this.cmd = cmd;
		this.userId = userId;
		this.userMessage = userMessage;
		errorMessage = null;
	}	

}
