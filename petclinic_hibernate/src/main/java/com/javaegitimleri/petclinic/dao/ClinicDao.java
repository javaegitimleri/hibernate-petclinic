package com.javaegitimleri.petclinic.dao;

import org.hibernate.SessionFactory;

import com.javaegitimleri.petclinic.model.Clinic;

public class ClinicDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Clinic findById(Long id) {
		return sessionFactory.getCurrentSession().get(Clinic.class, id);
	}
}
