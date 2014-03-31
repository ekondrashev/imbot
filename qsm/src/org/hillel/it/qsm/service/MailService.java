package org.hillel.it.qsm.service;

import java.util.List;

import org.hillel.it.qsm.model.search.MessageCriteria;

public interface MailService {

	public List<String> SearchProducts(MessageCriteria criteria);
	
}
