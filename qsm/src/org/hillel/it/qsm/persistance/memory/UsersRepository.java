package org.hillel.it.qsm.persistance.memory;

import java.util.HashMap;
import java.util.Map;

import org.hillel.it.qsm.model.entities.Message;
import org.hillel.it.qsm.model.entities.User;
import org.hillel.it.qsm.persistance.repository.UserRepository;

public class UsersRepository implements UserRepository{
	private Map<String,User> users;

	public UsersRepository() {
		this.users = new HashMap<String,User>();
	}

	@Override
	public void saveMessage(String email, Message message) {
		users.get(email).getMessages().add(message);
		
	}
	
	@Override
	public Message createMessage(String theme, String senderMail, String recieverMail, String text) {
		Message newMessage = new Message(theme, senderMail, recieverMail, text);
		return newMessage;
	}

	@Override
	public void deleteMessage(String email, Message message) {
		users.get(email).getMessages().remove(message);
		
	}

	public Map<String, User> getUsers() {
		return users;
	}

	
	
	
	
	
//	public void sendMessage(String theme, String recieverMail, String text) {
//		if (users.containsKey(recieverMail)) {
//			Message newMessage = new Message(theme, email, recieverMail, text);
//			users.get(recieverMail).getMessages().add(newMessage);
//		} else {
//			System.out
//					.println("Вы пытаетесь отправить письмо на не существующий адресс");
//		}
//	}
//
//	public void getMessage() {
//		this.inbox = server.getUsers().get(email).getInbox();
//		System.out.println(inbox.get(0).getMessage());
//	}
}
