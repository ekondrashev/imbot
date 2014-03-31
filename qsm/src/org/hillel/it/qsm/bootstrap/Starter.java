package org.hillel.it.qsm.bootstrap;

import org.hillel.it.qsm.persistance.memory.MessagesRepository;
import org.hillel.it.qsm.service.MailService;
import org.hillel.it.qsm.service.impl.MailServiceImpl;

public class Starter {
	public static void main(String[] args) {
		MessagesRepository messageRepository = new MessagesRepository();
		MailService service = new MailServiceImpl(messageRepository);
		
	}
}
