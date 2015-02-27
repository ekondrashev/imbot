package skypePackage;

import com.google.code.chatterbotapi.ChatterBot;
import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;
import com.skype.Chat;
import com.skype.ChatMessage;
import com.skype.ChatMessageListener;
import com.skype.SkypeException;

public class MyListener implements ChatMessageListener {

	private void myListener(String myMessage, Chat myCharts) throws Exception {
		ChatterBotFactory myFactory = new ChatterBotFactory();
		ChatterBot skypeBot = myFactory.create(ChatterBotType.JABBERWACKY);
		ChatterBotSession skypeSession = skypeBot.createSession();
		try {
			myMessage = skypeSession.think(myMessage);
			final Chat chatterup = myCharts;
			chatterup.send(myMessage);
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
