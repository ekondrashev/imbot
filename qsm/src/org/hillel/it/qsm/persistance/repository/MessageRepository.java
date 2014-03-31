package org.hillel.it.qsm.persistance.repository;

public interface MessageRepository{
	public void sendMessage(String theme, String recieverMail, String text);
	public void getMessage();
	public void deleteMessage();
}
