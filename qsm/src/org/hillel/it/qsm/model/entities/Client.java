package org.hillel.it.qsm.model.entities;

import java.util.List;

public class Client {
	private String email;
	private List<Message> inbox;
	private List<Message> outbox;
	private Server server;

	public Client(String email, String password, Server server) {
		if (server.getUsers().containsKey(email)) {
			if (server.getUsers().get(email).getPassword() == password) {
				this.email = email;
				this.inbox = server.getUsers().get(email).getInbox();
				this.outbox = server.getUsers().get(email).getOutbox();
				this.server = server;
			} else {
				System.out.println("неправильно введён пароль");
			}
		} else {
			User user = new User(email, password);
			server.getUsers().put(email, user);
			this.email = email;
			this.inbox = server.getUsers().get(email).getInbox();
			this.outbox = server.getUsers().get(email).getOutbox();
			this.server = server;
		}
	}

	public void sendMessage(String theme, String recieverMail, String text) {
		if (server.getUsers().containsKey(recieverMail)) {
			Message newMessage = new Message(theme, email, recieverMail, text);
			server.getUsers().get(recieverMail).getInbox().add(newMessage);
		} else {
			System.out
					.println("Вы пытаетесь отправить письмо на не существующий адресс");
		}
	}

	public void getMessage() {
		this.inbox = server.getUsers().get(email).getInbox();
		System.out.println(inbox.get(0).getMessage());
	}
}
