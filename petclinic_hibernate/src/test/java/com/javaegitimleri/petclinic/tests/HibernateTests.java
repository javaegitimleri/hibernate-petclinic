package com.javaegitimleri.petclinic.tests;

import java.util.Date;
import java.util.List;

import org.hibernate.IdentifierLoadAccess;
import org.hibernate.MultiIdentifierLoadAccess;
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
	public void testMultiIdentifierLoadAccess() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		MultiIdentifierLoadAccess<Pet> multiIdentifierLoadAccess = session.byMultipleIds(Pet.class);
		
		List<Pet> pets = multiIdentifierLoadAccess.multiLoad(1L,2L,3L,4L,5L);
		System.out.println("--- pets loaded ---");
		pets.forEach(pet->{
			System.out.println(pet.getName());
		});
	}
	
	@Test
	public void testIdentifierLoadAccess() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		IdentifierLoadAccess<Pet> identifierLoadAccess = session.byId(Pet.class);
		
		Pet pet1 = identifierLoadAccess.load(1L);
		
		System.out.println("--- pet 1 loaded ---");
		
		System.out.println(pet1.getName());
		System.out.println(pet1.getClass());
		
		Pet pet2 = identifierLoadAccess.getReference(2L);
		
		System.out.println("--- pet 2 loaded ---");
		
		System.out.println(pet2.getName());
		System.out.println(pet2.getClass());
	}
	
	@Test
	public void testLoad() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Pet pet = session.load(Pet.class, 1L);
		System.out.println("--- pet loaded ---");
		if(pet == null) {
			System.out.println("pet is null returning");
			return;
		}
		System.out.println(pet.getName());
		System.out.println(pet.getClass());
		System.out.println("--- session closed ---");
		tx.commit();
		session.close();
		
		System.out.println(pet.getBirthDate());
		
//		Pet pet2 = session.get(Pet.class, 1L);
//		System.out.println("--- pet loaded second time ---");
//		System.out.println(pet2.getName());
//		System.out.println(pet == pet2);
	}
	
	@Test
	public void testGet() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Pet pet = session.get(Pet.class, 1L);
		System.out.println("--- pet loaded ---");
		if(pet == null) {
			System.out.println("pet is null returning");
			return;
		}
		tx.commit();
		session.close();
		System.out.println(pet.getName());
		System.out.println(pet.getClass());
//		Pet pet2 = session.get(Pet.class, 1L);
//		System.out.println("--- pet loaded second time ---");
//		System.out.println(pet2.getName());
//		System.out.println(pet == pet2);
	}
	
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
	
	@Test
	public void testOneToOneLazyProblem() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		
		Image image = session.get(Image.class, 1L);
		System.out.println("---image loaded---");
		System.out.println(new String(image.getImageContent().getContent()));
		System.out.println(image.getImageContent().getClass());
		tx.commit();
		session.close();
	}
}
