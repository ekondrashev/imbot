package org.hillel.it.qsm.model.entities;

public class Message {
	private String theme;
	private String senderMail;
	private String recieverMail;
	private String text;
	
	public Message (String theme, String senderMail, String recieverMail, String text) {
		this.theme = theme;
		this.senderMail = senderMail;
		this.recieverMail = recieverMail;
		this.text = text;
	}
	
	public String getMessage() {
		return text;
	}
	
	
}
