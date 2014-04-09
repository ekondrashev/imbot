package org.hillel.it.qsm.persistance.memory;

import java.awt.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.hillel.it.qsm.model.entities.Message;
import org.hillel.it.qsm.model.entities.User;
import org.hillel.it.qsm.persistance.repository.UserRepository;

public class UsersRepository implements UserRepository {
	private Map<String, User> users;

	public UsersRepository() {
		this.users = new HashMap<String, User>();
	}

	@Override
	public void deleteMessage(String email, Message message,int id) {

		users.get(email).getMessages().remove(id);

	}

	public Map<String, User> getUsers() {
		return users;
	}

	public void sendMessage(String theme, String recieverMail,
			String senderEmail, String text) {
		if (users.containsKey(recieverMail)) {
			Message newMessage = new Message(theme, senderEmail, recieverMail,
					text);
			users.get(recieverMail).getMessages().put(newMessage.getId(), newMessage);
			users.get(senderEmail).getMessages().put(newMessage.getId(), newMessage);
		} else {
			System.out
					.println("Вы пытаетесь отправить письмо на не существующий адресс");
		}
	}

	@Override
	public void getInbox(String email) {
	  
	  Iterator iterator = users.get(email).getMessages().values().iterator();
	  while(iterator.hasNext()) {
		  if (message.getRecieverMail() == email) {
				System.out.println("Дата создания: " + message.getCreated());
				System.out.println("Получатель: " + message.getRecieverMail());
				System.out.println("Отправитель: " + message.getSenderMail());
				System.out.println("Тема: " + message.getTheme());
				System.out.println("Текст: " + message.getText());
				System.out.println("id " + message.getId());
				System.out.println("-----------входящее------------------");

			}
	  }
	  
	      }
		for (Message message : users.get(email).getMessages()) {
			if (message.getRecieverMail() == email) {
				System.out.println("Дата создания: " + message.getCreated());
				System.out.println("Получатель: " + message.getRecieverMail());
				System.out.println("Отправитель: " + message.getSenderMail());
				System.out.println("Тема: " + message.getTheme());
				System.out.println("Текст: " + message.getText());
				System.out.println("id " + message.getId());
				System.out.println("-----------входящее------------------");

			}
		}
	}

	@Override
	public void getOutbox(String email) {
		for (Message message : users.get(email).getMessages()) {
			if (message.getSenderMail() == email) {

				System.out.println("Дата создания: " + message.getCreated());
				System.out.println("Получатель: " + message.getRecieverMail());
				System.out.println("Отправитель: " + message.getSenderMail());
				System.out.println("Тема: " + message.getTheme());
				System.out.println("Текст: " + message.getText());
				System.out.println("id " + message.getId());
				System.out.println("-----исходящее---------");
			}
		}

	}

}
