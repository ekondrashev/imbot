package org.hillel.it.qsm.service.impl;

import static org.junit.Assert.*;

import org.hillel.it.qsm.model.entities.Message;
import org.hillel.it.qsm.model.entities.User;
import org.hillel.it.qsm.persistance.memory.UsersRepository;
import org.hillel.it.qsm.persistance.repository.UserRepository;
import org.hillel.it.qsm.service.MailService;
import org.junit.Test;

public class MailServiceImplTest {

	@Test
	public void MailServiceImplConstructorTest() {
		UserRepository userRepository = new UsersRepository();

		MailService service = new MailServiceImpl("example@qsm.com", "123xxx",
				userRepository);
		MailService service1 = new MailServiceImpl("example@qsm.com", "123xxx",
				userRepository);
		MailService service2 = new MailServiceImpl("example@qsm.com", "1232xx",
				userRepository);
		
	}
	
	@Test
	public void sendMessageTest() {
		UserRepository userRepository = new UsersRepository();

		MailService service = new MailServiceImpl("example@qsm.com", "123xxx",
				userRepository);
		service.sendMessage("theme", "mail@mail.com", "text");
		
	}

}
