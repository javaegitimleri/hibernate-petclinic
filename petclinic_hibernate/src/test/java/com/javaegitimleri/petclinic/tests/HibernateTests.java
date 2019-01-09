package com.javaegitimleri.petclinic.tests;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.javaegitimleri.petclinic.config.HibernateConfig;
import com.javaegitimleri.petclinic.model.Address;
import com.javaegitimleri.petclinic.model.Image;
import com.javaegitimleri.petclinic.model.Owner;
import com.javaegitimleri.petclinic.model.OwnerWithCompositePK;
import com.javaegitimleri.petclinic.model.OwnerWithCompositePK.OwnerId;
import com.javaegitimleri.petclinic.model.Pet;
import com.javaegitimleri.petclinic.model.Rating;
import com.javaegitimleri.petclinic.model.Visit;

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
		OwnerWithCompositePK owner = new OwnerWithCompositePK();
		
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
	
	@Test
	public void testEmbeddable() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		
		Owner owner = new Owner();
		owner.setFirstName("Kenan");
		owner.setLastName("Sevindik");
		owner.setRating(Rating.PREMIUM);
		
		Address address = new Address();
		address.setStreet("Dumlupýnar Bulv.");
		address.setPhone("3122101036");
		
		owner.setAddress(address);
		
		session.persist(owner);
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void testMappedBy() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		
		Owner owner = session.get(Owner.class, 1L);
		Pet pet = session.get(Pet.class, 101L);
		
		//owner.getPets().add(pet);
		
		//pet.setOwner(owner);
		
		//owner.getPets().remove(pet);
		
		pet.setOwner(null);
		
		//session.update(owner);
		//session.merge(owner);
		
		tx.commit();
		session.close();
		
	}
	
	@Test
	public void testParentChildAssoc() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		
		Pet pet = session.get(Pet.class, 1L);
		Visit visit = session.get(Visit.class, 101L);
		Image image = session.get(Image.class, 1001L);
		
		pet.getVisits().remove(visit);
		pet.getImagesByFilePath().remove("/myimage");
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void testLazyEagerAccess() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		
		Pet pet = session.get(Pet.class, 101L);
		System.out.println("---pet loaded---");
		
		System.out.println("visits size :" + pet.getVisits().size());
		System.out.println("---");
		System.out.println("pet type name :" + pet.getType().getName());
		System.out.println(pet.getType().getClass());
		
		tx.commit();
		session.close();

		
		
	}
}
