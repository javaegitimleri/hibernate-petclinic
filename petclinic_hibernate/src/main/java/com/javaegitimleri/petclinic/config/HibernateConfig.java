package com.javaegitimleri.petclinic.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	static {
		Configuration cfg = new Configuration().configure();
		sessionFactory = cfg.buildSessionFactory();
	}
}
