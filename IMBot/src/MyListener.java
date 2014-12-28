//package skypePackage;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.code.chatterbotapi.ChatterBot;
import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;
import com.skype.Chat;
import com.skype.ChatMessage;
import com.skype.ChatMessageListener;
import com.skype.SkypeException;

public class MyListener implements ChatMessageListener {
	Map<String, Chat> map = new LinkedHashMap<String, Chat>();

	MyListener(Map<String, Chat> map) {
		this.map = map;
	};

	private void myListener(String myMessage, Chat myCharts) throws Exception {
		try {
			map.put(myCharts.getId(), myCharts);

			String mesLine = "--cmd=send_message " + "--user_id="
					+ myCharts.getId() + " --message=" + myMessage;
			Send.sendMessage(mesLine);
		} catch (final SkypeException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void chatMessageReceived(ChatMessage recMessage)
			throws SkypeException {
		try {
			myListener(recMessage.getContent(), recMessage.getChat());
			System.out.println("\n" + recMessage.getSenderDisplayName() + " : "
					+ recMessage.getContent());
		} catch (final SkypeException ex) {
			ex.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void chatMessageSent(ChatMessage sentMessage) throws SkypeException {
		try {
			System.out.println("\n" + sentMessage.getSenderDisplayName()
					+ " : " + sentMessage.getContent());
		} catch (final SkypeException ex) {
			ex.printStackTrace();
		}

	}

}
