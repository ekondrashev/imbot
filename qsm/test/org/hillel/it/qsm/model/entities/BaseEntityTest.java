package org.hillel.it.qsm.model.entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class BaseEntityTest {

	@Test
	public void getCreatedTest() {
		Message message = new Message(null, null, null, null);
		assertNotNull(message.getCreated());
	}
	
	@Test
	public void getIdTest() {
		Message message = new Message(null, null, null, null);
		assertNotNull(message.getId());
	}

}
