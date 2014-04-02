package org.hillel.it.qsm.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hillel.it.qsm.model.entities.Message;
import org.hillel.it.qsm.model.entities.User;
import org.hillel.it.qsm.model.search.MessageCriteria;
import org.hillel.it.qsm.persistance.repository.UserRepository;
import org.hillel.it.qsm.service.MailService;

public class MailServiceImpl implements MailService {

	private String email;
	private UserRepository users;
	private List<Message> inbox;
	private List<Message> outbox;

	public MailServiceImpl(String email, String password, UserRepository users) {
		inbox = new ArrayList();
		outbox = new ArrayList();
		if (users.getUsers().containsKey(email)) {
			if (users.getUsers().get(email).getPassword() == password) {
				this.email = email;
				this.users = users;

			} else {
				System.out.println("неправильно введён пароль");
			}
		} else {
			User user = new User(email, password);
			users.getUsers().put(email, user);
			this.email = email;
			this.users = users;
		}
	}

	@Override
	public List<String> SearchProducts(MessageCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendMessage(String theme, String recieverMail, String text) {
		if (users.getUsers().containsKey(recieverMail)) {
			users.saveMessage(recieverMail,
					users.createMessage(theme, email, recieverMail, text));
			users.saveMessage(email,
					users.createMessage(theme, email, recieverMail, text));
		} else {
			System.out
					.println("Вы пытаетесь отправить письмо на не существующий адресс");
		}
	}

	@Override
	public void getMessages() {

//		 for (Iterator<Message> iterator =
//		 users.getUsers().get(email).getMessages().iterator();
//		 iterator.hasNext();) {
//		 Message message = (Message) iterator.next();
//		
//	     }

		for (Message message : users.getUsers().get(email).getMessages()) {
			if (message.getSenderMail() == email) {
				outbox.add(message);
				System.out.println("Дата создания: " + message.getCreated());
				System.out.println("Получатель: " + message.getRecieverMail());
				System.out.println("Отправитель: " + message.getSenderMail());
				System.out.println("Тема: " + message.getTheme());
				System.out.println("Текст: " + message.getText());
				System.out.println("-----исходящее---------");
			} else if (message.getRecieverMail() == email && message.getSenderMail() != email) {
				inbox.add(message);
				System.out.println("Дата создания: " + message.getCreated());
				System.out.println("Получатель: " + message.getRecieverMail());
				System.out.println("Отправитель: " + message.getSenderMail());
				System.out.println("Тема: " + message.getTheme());
				System.out.println("Текст: " + message.getText());
				System.out.println("-----------входящее------------------");
			}

		}

	}

}