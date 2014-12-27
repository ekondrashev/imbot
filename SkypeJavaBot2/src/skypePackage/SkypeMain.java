package skypePackage;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.skype.ChatMessage;
import com.skype.Skype;
import com.skype.SkypeException;

public class SkypeMain {

	public static void main(String[] args) throws SkypeException, InterruptedException {
		JOptionPane.showMessageDialog(null, "Start listener");
		Map<String,ChatMessage> map=new LinkedHashMap<>();
		
		new SkypeMain();
		
		Skype.addChatMessageListener(new MyListener(map));
		Skype.setDaemon(false);
		
		Recv rdRecive = new Recv("FromDB","127.0.0.1",map);
		Thread threadRecive = new Thread(rdRecive);
		threadRecive.setName("Recive");
		threadRecive.start();
		threadRecive.join();
	}

}
