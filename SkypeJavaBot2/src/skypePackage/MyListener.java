package skypePackage;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.skype.ChatMessage;
import com.skype.ChatMessageListener;
import com.skype.SkypeException;

public class MyListener implements ChatMessageListener {
	
	private static final Logger log = Logger.getLogger(MyListener.class);
	
	Map<String,ChatMessage> map=new LinkedHashMap<>();
	
	public MyListener(Map<String,ChatMessage> map) {
		this.map=map;
	}
	
	private void myListener(ChatMessage myMessage) throws Exception {
		String id = UUID.randomUUID().toString();
		map.put(id, myMessage);
		//send message to rabbitMQ
		String ourMessage= "--cmd=ask^-text=\""+myMessage.getContent().toLowerCase()+"\"^-apiname=Skype^user="+myMessage.getSenderId()+"^-key="+id;
		log.info(ourMessage);
		Send snd=new Send("ToDB","127.0.0.1");
		snd.sendMessage(ourMessage);
	}

	@Override
	public void chatMessageReceived(ChatMessage recMessage)
			throws SkypeException {
		try {
			myListener(recMessage);
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
