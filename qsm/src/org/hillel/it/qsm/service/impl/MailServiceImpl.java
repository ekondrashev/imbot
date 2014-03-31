package org.hillel.it.qsm.service.impl;

import java.util.List;

import org.hillel.it.qsm.model.search.MessageCriteria;
import org.hillel.it.qsm.persistance.memory.MessagesRepository;
import org.hillel.it.qsm.persistance.repository.MessageRepository;
import org.hillel.it.qsm.service.MailService;

public class MailServiceImpl implements MailService{
	
	private MessageRepository productRepository;

	public MailServiceImpl(MessagesRepository messageRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public List<String> SearchProducts(MessageCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
