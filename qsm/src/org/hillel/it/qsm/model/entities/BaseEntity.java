package org.hillel.it.qsm.model.entities;

import java.util.Date;

public abstract class BaseEntity {
	protected int id;
	protected Date created;
	public Date getCreated() {
		return created;
	}
	public BaseEntity() {
		created = new Date();
	}
}
