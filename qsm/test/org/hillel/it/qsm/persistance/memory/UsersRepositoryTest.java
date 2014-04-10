package org.hillel.it.qsm.persistance.memory;

import static org.junit.Assert.*;

import org.hillel.it.qsm.model.entities.Message;
import org.hillel.it.qsm.persistance.repository.UserRepository;
import org.hillel.it.qsm.service.MailService;
import org.hillel.it.qsm.service.impl.MailServiceImpl;
import org.junit.Test;

public class UsersRepositoryTest {

	@Test
	public void UsersRepositoryConstructorTest() {
		UserRepository userRepository = new UsersRepository();
	}

	@Test
	public void deleteMessageTest() {
		UserRepository userRepository = new UsersRepository();

		MailService service = new MailServiceImpl("example@qsm.com", "123xxx",
				userRepository);
		MailService service1 = new MailServiceImpl("example1@qsm.com",
				"123xxx", userRepository);

	}

	@Test
	public void getInboxTest() {

		UserRepository userRepository = new UsersRepository();

		MailService service = new MailServiceImpl("example@qsm.com", "123xxx",
				userRepository);
		MailService service1 = new MailServiceImpl("example1@qsm.com",
				"123xxx", userRepository);
		// Добавить прокси
		service1.sendMessage("123", "example@qsm.com", "Hello!!");
		service.sendMessage("hello world", "example1@qsm.com", "Hello!!");

		service.getInbox();
	}

	@Test
	public void getOutboxTest() {

		UserRepository userRepository = new UsersRepository();

		MailService service = new MailServiceImpl("example@qsm.com", "123xxx",
				userRepository);
		MailService service1 = new MailServiceImpl("example1@qsm.com",
				"123xxx", userRepository);
		// Добавить прокси
		service1.sendMessage("123", "example@qsm.com", "Hello!!");
		service.sendMessage("hello world", "example1@qsm.com", "Hello!!");

		service.getOutbox();

	}

	@Test
	public void getTrashTest() {

		UserRepository userRepository = new UsersRepository();

		MailService service = new MailServiceImpl("example@qsm.com", "123xxx",
				userRepository);
		MailService service1 = new MailServiceImpl("example1@qsm.com",
				"123xxx", userRepository);
		// Добавить прокси
		service1.sendMessage("123", "example@qsm.com", "Hello!!");
		service.sendMessage("hello world", "example1@qsm.com", "Hello!!");
		service.getInbox();
		service.getTrash();

	}
	@Test
	public void clearTrashTest() {

		UserRepository userRepository = new UsersRepository();

		MailService service = new MailServiceImpl("example@qsm.com", "123xxx",
				userRepository);
		MailService service1 = new MailServiceImpl("example1@qsm.com",
				"123xxx", userRepository);
		// Добавить прокси
		service1.sendMessage("123", "example@qsm.com", "Hello!!");
		service.sendMessage("hello world", "example1@qsm.com", "Hello!!");
		service.getInbox();
		service.getOutbox();
		service.deleteMessage(6);
		                     
		
		
		service.clearTrash();
		
	

	}

}
