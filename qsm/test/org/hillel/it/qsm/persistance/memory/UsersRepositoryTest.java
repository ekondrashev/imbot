package org.hillel.it.qsm.persistance.memory;

import static org.junit.Assert.*;

import org.hillel.it.qsm.model.entities.Message;
import org.hillel.it.qsm.persistance.repository.UserRepository;
import org.junit.Test;

public class UsersRepositoryTest {

	@Test
	public void UsersRepositoryConstructorTest() {
		UserRepository userRepository = new UsersRepository();
	}
	public void deleteMessageTest(){
		UserRepository userRepository = new UsersRepository();
		Message message = new Message("lol","lax1@mail.ru","lax2@mail.ru","lololo");
		userRepository.deleteMessage("lax2@mail.ru", message.getId());
		assertNull(message.getTheme());
		
	}

}
