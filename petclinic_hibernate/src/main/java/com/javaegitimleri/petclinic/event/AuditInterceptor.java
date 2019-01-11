package com.javaegitimleri.petclinic.event;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class AuditInterceptor extends EmptyInterceptor {
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		System.out.println(">>>Entity saved :" + entity + " with id :" + id);
		return false;
	}
	
	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		System.out.println(">>>Entity updated :" + entity);
		return false;
	}
	
	@Override
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		System.out.println(">>>Entity deleted :" + entity);
	}
}
