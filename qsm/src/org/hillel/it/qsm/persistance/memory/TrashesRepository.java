package org.hillel.it.qsm.persistance.memory;

import java.util.HashMap;
import java.util.Map;

import org.hillel.it.qsm.model.entities.Message;
import org.hillel.it.qsm.model.entities.User;
import org.hillel.it.qsm.persistance.repository.TrashRepository;

public class TrashesRepository implements TrashRepository {
	private Map<Integer, Message> trash;

	public TrashesRepository() {
		trash = new HashMap<Integer, Message>();
	}

	@Override
	public void fullDelete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearTrash() {
		// TODO Auto-generated method stub

	}

	@Override
	public void recoverMessage(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteMessage(int id) {
		// TODO Auto-generated method stub
		
	}

}
