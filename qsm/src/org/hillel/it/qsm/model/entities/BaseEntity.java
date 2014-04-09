package org.hillel.it.qsm.model.entities;

import java.util.Date;

public abstract class BaseEntity {
	protected static int counter;
	protected int id;
	protected Date created;
	public Date getCreated() {
		return created;
	}
	public int getId() {
		counter++;
		return id+counter;
	}
	public BaseEntity() {
		created = new Date();
		
	}
}
