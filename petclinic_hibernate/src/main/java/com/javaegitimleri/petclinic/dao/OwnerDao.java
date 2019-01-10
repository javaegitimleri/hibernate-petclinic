package com.javaegitimleri.petclinic.dao;

import org.hibernate.SessionFactory;

import com.javaegitimleri.petclinic.model.Owner;

public class OwnerDao {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void create(Owner owner) {
		sessionFactory.getCurrentSession().persist(owner);
	}
}
