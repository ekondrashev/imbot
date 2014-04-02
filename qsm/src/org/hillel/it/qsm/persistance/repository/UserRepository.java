package org.hillel.it.qsm.persistance.repository;

import java.util.Map;

import org.hillel.it.qsm.model.entities.Message;
import org.hillel.it.qsm.model.entities.User;

public interface UserRepository {
	public void saveMessage(String email, Message message);
	public void deleteMessage(String email, Message message);
	public Message createMessage(String theme, String senderMail, String recieverMail, String text);
	public Map<String, User> getUsers();
}
