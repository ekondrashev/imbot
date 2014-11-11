package skypePackage;

import com.skype.Skype;
import com.skype.SkypeException;

public class SkypeMain {

	public static void main(String[] args) throws SkypeException {
		
		new SkypeMain();
		
		Skype.addChatMessageListener(new MyListener());
		Skype.setDaemon(false);
	}

}
