package com.javaegitimleri.petclinic.tests;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.javaegitimleri.petclinic.config.HibernateConfig;
import com.javaegitimleri.petclinic.model.Owner;
import com.javaegitimleri.petclinic.model.Pet;
import com.javaegitimleri.petclinic.model.Owner.OwnerId;

public class HibernateTests {
	@Test
	public void testHibernateSetup() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		tx.commit();
		session.close();
		HibernateConfig.getSessionFactory().close();
	}
	
	@Test
	public void testCreateEntity() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Pet pet = new Pet();
		//pet.setId(1L);
		pet.setName("kedicik");
		
		session.persist(pet);
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void testFieldLevelAccess() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Pet pet = new Pet("kedicik", new Date());
		pet.setId(1L);
		
		session.persist(pet);
		
		tx.commit();
		session.close();
		
		session = HibernateConfig.getSessionFactory().openSession();
		
		Pet pet2 = session.get(Pet.class, 1L);
		
		System.out.println(pet2);
	}
	
	@Test
	public void testWithoutTX() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		
		Pet pet = new Pet("kedicik", new Date());
		pet.setId(1L);
		
		session.persist(pet);
		
		//session.flush();
		tx.commit();
		
		session.close();
		
	}
	
	@Test
	public void testCheckNullability() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		
		Pet pet = new Pet();
		pet.setId(1L);
		
		session.persist(pet);
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void testCompositePK() {
		Owner owner = new Owner();
		
		OwnerId id = new OwnerId();
		id.setFirstName("Kenan");
		id.setLastName("Sevindik");
		
		owner.setId(id);
		
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		
		session.persist(owner);
		
		tx.commit();
		session.close();
	}
}
