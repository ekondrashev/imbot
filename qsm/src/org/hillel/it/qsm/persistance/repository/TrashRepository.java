package org.hillel.it.qsm.persistance.repository;

import java.util.Map;



public interface TrashRepository {
	public void fullDelete(int id);
	public void clearTrash();
	public void recoverMessage(int id);
	public void deleteMessage(int id);
}
