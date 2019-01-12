package com.javaegitimleri.petclinic.tests;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;
import org.junit.Test;

import com.javaegitimleri.petclinic.config.JpaConfig;
import com.javaegitimleri.petclinic.model.Owner;
import com.javaegitimleri.petclinic.model.Pet;
import com.javaegitimleri.petclinic.model.Rating;
import com.javaegitimleri.petclinic.model.Visit;

public class JpaTests {
	
	@Test
	public void testJpql() {
		EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
		
		String queryString = "select p from Pet p where p.name like :petName or p.type.id = :typeId";
		
		TypedQuery<Pet> typedQuery = entityManager.createQuery(queryString, Pet.class);
		
		typedQuery.setParameter("petName", "K%");
		typedQuery.setParameter("typeId", 2L);
		
		List<Pet> resultList = typedQuery.getResultList();
		
		System.out.println("--- query executed ---");
		
		resultList.forEach(System.out::println);
	}
	
	@Test
	public void testLifecycleCallbacks() {
		EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		
		Pet pet = new Pet("kedicik 6",new Date());
		
		entityManager.persist(pet);
		
		Pet pet2 = entityManager.find(Pet.class, 136L);
		
		pet2.setBirthDate(null);
		
		entityManager.remove(entityManager.getReference(Pet.class, 132L));
		
		tx.commit();
		entityManager.close();
	}
	
	@Test
	public void testDelete() {
		EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		
		Visit visit = entityManager.find(Visit.class, 3L);
		
		entityManager.clear();
		
		visit = entityManager.merge(visit);
		
		entityManager.remove(visit);
		
		tx.commit();
		entityManager.close();
	}
	
	@Test
	public void testHibernateApiAccess2() {
		EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		
		Session session = (Session) entityManager.getDelegate();
		Session session2 = entityManager.unwrap(Session.class);
		
		System.out.println(session == session2);
		
		SessionFactory sf = session.getSessionFactory();
		
		Statistics statistics = sf.getStatistics();
		session.setHibernateFlushMode(FlushMode.MANUAL);
	}
	
	@Test
	public void testHibernateApiAccess() {
		EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		
		SessionFactory sf = (SessionFactory)JpaConfig.getEntityManagerFactory();
		Session s = (Session) entityManager;
		Transaction hibTx = (Transaction)tx;
		
		Statistics statistics = sf.getStatistics();
		s.setHibernateFlushMode(FlushMode.MANUAL);
	}
	
	@Test
	public void testFindAndGetReference() {
		EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		
		Pet pet = entityManager.find(Pet.class, 1L);
		
		System.out.println("--- pet loaded ---");
		
		System.out.println(pet.getName());
		System.out.println(pet.getClass());
		
		Pet pet2 = entityManager.getReference(Pet.class, 2L);
		
		System.out.println("--- pet 2 loaded ---");
		
		System.out.println(pet2.getName());
		System.out.println(pet2.getClass());
	}
	
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
	
	@Test
	public void testRating() {
		EntityManager entityManager = JpaConfig.getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		
		Owner owner = new Owner();
		owner.setFirstName("Kenan");
		owner.setLastName("Sevindik");
		owner.setRating(Rating.PREMIUM);
		
		entityManager.persist(owner);
		
		tx.commit();
		
		entityManager.close();
	}
}
