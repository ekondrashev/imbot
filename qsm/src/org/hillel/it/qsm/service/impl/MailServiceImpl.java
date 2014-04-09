package org.hillel.it.qsm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hillel.it.qsm.model.entities.Message;
import org.hillel.it.qsm.model.entities.User;
import org.hillel.it.qsm.model.search.MessageCriteria;
import org.hillel.it.qsm.persistance.repository.TrashRepository;
import org.hillel.it.qsm.persistance.repository.UserRepository;
import org.hillel.it.qsm.service.MailService;

public class MailServiceImpl implements MailService {

	private String email;
	private UserRepository users;
	private TrashRepository trash;

	public MailServiceImpl(String email, String password, UserRepository users,TrashRepository trash) {
	
		if (users.getUsers().containsKey(email)) {
			if (users.getUsers().get(email).getPassword() == password) {
				this.email = email;
				this.users = users;
				this.trash = trash;

			} else {
				System.out.println("неправильно введён пароль");
			}
		} else {
			User user = new User(email, password);
			users.getUsers().put(email, user);
			this.email = email;
			this.users = users;
			this.trash = trash;
		}
	}

	@Override
	public List<String> SearchProducts(MessageCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendMessage(String theme, String recieverMail, String text) {
	users.sendMessage(theme, recieverMail, email, text);
	}

	@Override
	public void getInbox() {
		users.getInbox(email);
		
	}

	@Override
	public void getOutbox() {
		users.getOutbox(email);
		
	}

	

}