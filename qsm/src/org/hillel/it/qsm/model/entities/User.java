package org.hillel.it.qsm.model.entities;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseEntity{
	private String email;
	private String password;
	List<Message> messages;

	public User(String email, String password) {
		this.email = email;
		this.password = password;
		messages = new ArrayList();
	}

	public String getPassword() {
		return password;
	}

	public List<Message> getMessages() {
		return messages;
	}

}
