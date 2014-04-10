package org.hillel.it.qsm.model.entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	public void UserconstructorTest() {
		User user = new User("123@mail.ru", "7");
		
	}
	@Test
	public void getPasswordTest() {
		User user = new User("123@mail.ru", "7");
		assertEquals(user.getPassword(), "7");
	}
	@Test
	public void getMessagesTest() {
		User user = new User("123@mail.ru", "7");
		assertNotNull(user.getMessages());
	}
}
