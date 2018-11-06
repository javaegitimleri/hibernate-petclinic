package com.javaegitimleri.petclinic.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;

import com.javaegitimleri.petclinic.config.JpaConfig;

public class JpaTests {
	@Test
	public void testJpaSetup() {
		EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		tx.commit();
		entityManager.close();
		JpaConfig.getEntityManagerFactory().close();
	}
}
