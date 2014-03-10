package org.hillel.it.qsm.model.entities;
import Message;


public class MailBox {
	Message[] inbox;
	Message[] outbox;
	Message[] trash;
	public Message[] getInbox() {
		return inbox;
	}
	public void setInbox(Message[] inbox) {
		this.inbox = inbox;
	}
	public Message[] getOutbox() {
		return outbox;
	}
	public void setOutbox(Message[] outbox) {
		this.outbox = outbox;
	}
	public Message[] getTrash() {
		return trash;
	}
	public void setTrash(Message[] trash) {
		this.trash = trash;
	}
	
}
