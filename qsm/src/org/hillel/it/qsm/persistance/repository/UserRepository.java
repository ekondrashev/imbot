package org.hillel.it.qsm.persistance.repository;

import java.util.Map;

import org.hillel.it.qsm.model.entities.Message;
import org.hillel.it.qsm.model.entities.User;

public interface UserRepository {

	public void sendMessage(String theme, String recieverMail,String email, String text);
	public void deleteMessage(String email, Message message,int id);
	public Map<String, User> getUsers();
	public void getInbox(String email);
	public void getOutbox(String email);
}
