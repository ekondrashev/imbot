package org.hillel.it.qsm.model.entities;

import java.util.HashMap;
import java.util.Map;

public class Server {
	private Map<String,User> users;

	public Server() {
		this.users = new HashMap<String,User>();
	}

	public Map<String, User> getUsers() {
		return users;
	}
	
	
	
}
