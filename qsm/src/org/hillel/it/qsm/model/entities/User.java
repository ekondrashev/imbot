package org.hillel.it.qsm.model.entities;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String email;
	private String password;
	private List<Message> inbox;
	private List<Message> outbox;

	public User (String email, String password) {
		this.email = email;
		this.password = password;
		inbox = new ArrayList<Message>();
		outbox = new ArrayList<Message>();
	}
	
	public String getPassword() {
		return password;
	}

	public List<Message> getInbox() {
		return inbox;
	}

	public List<Message> getOutbox() {
		return outbox;
	}
	
	

}
