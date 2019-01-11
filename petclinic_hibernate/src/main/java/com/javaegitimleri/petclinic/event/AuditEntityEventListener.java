package com.javaegitimleri.petclinic.event;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

import com.javaegitimleri.petclinic.model.BaseEntity;

public class AuditEntityEventListener {
	@PostPersist
	public void postInsert(BaseEntity entity) {
		System.out.println(">>>Entity inserted :" + entity);
	}
	
	@PostUpdate
	public void postUpdate(BaseEntity entity) {
		System.out.println(">>>Entity updated :" + entity);
	}
	
	@PostRemove
	public void postDelete(BaseEntity entity) {
		System.out.println(">>>Entity deleted :" + entity);
	}
}
