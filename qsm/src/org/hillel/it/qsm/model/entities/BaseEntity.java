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
		return id;
	}
	public BaseEntity() {
		created = new Date();
		counter++;
		id = id+counter;
	}
}
