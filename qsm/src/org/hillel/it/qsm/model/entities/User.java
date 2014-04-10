package org.hillel.it.qsm.model.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User extends BaseEntity {
	private String email;
	private String password;
	private Map<Integer, Message> messages;

	public User(String email, String password) {
		this.email = email;
		this.password = password;
		messages = new HashMap();

	}

	public String getPassword() {
		return password;
	}

	public Map<Integer, Message> getMessages() {
		return messages;

	}

	public String getEmail() {
		return email;
	}

}
