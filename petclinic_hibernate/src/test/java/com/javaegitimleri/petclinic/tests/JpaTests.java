package com.javaegitimleri.petclinic.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;

import com.javaegitimleri.petclinic.config.JpaConfig;
import com.javaegitimleri.petclinic.model.Pet;

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
	
	@Test
	public void testWithoutTX() {
		EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		
		Pet pet = new Pet();
		pet.setId(1L);
		pet.setName("kedicik");
		
		entityManager.persist(pet);
		
		//entityManager.flush();
		
		tx.commit();
		
		entityManager.close();
	}
}
