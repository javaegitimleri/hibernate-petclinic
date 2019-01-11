package com.javaegitimleri.petclinic.event;

import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;

public class AuditEntityInsertListener implements PostInsertEventListener {

	@Override
	public boolean requiresPostCommitHanding(EntityPersister persister) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onPostInsert(PostInsertEvent event) {
		System.out.println(">>>Entity inserted :" + event.getEntity() + " with id :" + event.getId());
	}

}
