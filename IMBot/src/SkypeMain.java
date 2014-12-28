import java.util.LinkedHashMap;
import java.util.Map;

import com.skype.Chat;
import com.skype.Skype;
import com.skype.SkypeException;

public class SkypeMain {

	public static void main(String[] args) throws SkypeException,
			InterruptedException {
		Map<String, Chat> map = new LinkedHashMap<String, Chat>();

		Skype.addChatMessageListener(new MyListener(map));
		Skype.setDaemon(false);

		Recv recv = new Recv(map);

		Thread myThready = new Thread(recv);
		myThready.start();
		myThready.join();
	}

}
