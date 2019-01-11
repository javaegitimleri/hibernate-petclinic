package com.javaegitimleri.petclinic.event;

import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.persister.entity.EntityPersister;

public class AuditEntityDeleteListener implements PostDeleteEventListener {

	@Override
	public boolean requiresPostCommitHanding(EntityPersister persister) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onPostDelete(PostDeleteEvent event) {
		System.out.println(">>>Entity deleted :" + event.getEntity());
	}

}
