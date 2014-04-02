package org.hillel.it.qsm.bootstrap;

import org.hillel.it.qsm.persistance.memory.UsersRepository;
import org.hillel.it.qsm.persistance.repository.UserRepository;
import org.hillel.it.qsm.service.MailService;
import org.hillel.it.qsm.service.impl.MailServiceImpl;

public class Starter {
	public static void main(String[] args) {
		UserRepository userRepository = new UsersRepository();
		MailService service = new MailServiceImpl("example@qsm.com", "123xxx", userRepository);
		MailService service1 = new MailServiceImpl("example1@qsm.com", "123xxx", userRepository);
		//Добавить прокси
		service1.sendMessage("123", "example@qsm.com", "Hello!!");
		service1.sendMessage("123", "example@qsm.com", "Hello!!1");
		service1.sendMessage("123", "example@qsm.com", "Hello!!2");
		service1.sendMessage("123", "example@qsm.com", "Hello!!3");
		service.sendMessage("hello world", "example1@qsm.com", "Hello!!");
		service.sendMessage("hello world1", "example1@qsm.com", "Hello!!1");
		service.sendMessage("hello world2", "example1@qsm.com", "Hello!!2");
		service.sendMessage("hello world3", "example1@qsm.com", "Hello!!3");
		
		service.getMessages();
		

		
	}
}
