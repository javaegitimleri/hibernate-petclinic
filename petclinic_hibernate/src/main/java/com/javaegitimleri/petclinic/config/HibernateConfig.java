package com.javaegitimleri.petclinic.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.javaegitimleri.petclinic.event.AuditInterceptor;

public class HibernateConfig {
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	static {
		Configuration cfg = new Configuration().setInterceptor(new AuditInterceptor()).configure();
		sessionFactory = cfg.buildSessionFactory();
	}
}
