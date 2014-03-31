package org.hillel.it.qsm.persistance.memory;

import java.util.List;

import org.hillel.it.qsm.model.entities.Message;
import org.hillel.it.qsm.persistance.repository.MessageRepository;

public class MessagesRepository implements MessageRepository{
	private List<Message> messages;

	@Override
	public void sendMessage(String theme, String recieverMail, String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getMessage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMessage() {
		// TODO Auto-generated method stub
		
	}
}
