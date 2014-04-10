package org.hillel.it.qsm.model.entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class MessageTest {

	@Test
	
	public void MessageConstructorTest() {
		Message message = new Message("lol","lax1@mail.ru","lax2@mail.ru","lololo");
		
		
	}
	
	@Test
	public void isInTrashTest() {
		 	Message message = new Message("lol","lax1@mail.ru","lax2@mail.ru","lololo");
		 	assertFalse(message.isInTrash());
	}
	@Test
	public void setInTrashTest() {
		Message message = new Message("lol","lax1@mail.ru","lax2@mail.ru","lololo");
		message.setInTrash(true);
	 	assertTrue(message.isInTrash());
	}
	@Test
	public void getThemeTest() {
		Message message = new Message("lol","lax1@mail.ru","lax2@mail.ru","lololo");
		assertNotNull(message.getTheme());
	}
	
	
	@Test
	public void getSenderMailTest() {
		Message message = new Message("lol","lax1@mail.ru","lax2@mail.ru","lololo");
		assertNotNull(message.getSenderMail());
	}
	@Test
	public void getRecieverMailTest() {
		Message message = new Message("lol","lax1@mail.ru","lax2@mail.ru","lololo");
		assertNotNull(message.getRecieverMail());
	}
	@Test
	public void getTextTest() {
		Message message = new Message("lol","lax1@mail.ru","lax2@mail.ru","lololo");
		assertNotNull(message.getText());
	}

}
