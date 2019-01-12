package com.javaegitimleri.petclinic.tests;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.FlushMode;
import org.hibernate.Hibernate;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.LockMode;
import org.hibernate.MultiIdentifierLoadAccess;
import org.hibernate.NaturalIdLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SimpleNaturalIdLoadAccess;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.QueryStatistics;
import org.hibernate.stat.Statistics;
import org.junit.Test;

import com.javaegitimleri.petclinic.config.HibernateConfig;
import com.javaegitimleri.petclinic.dao.ClinicDao;
import com.javaegitimleri.petclinic.dao.OwnerDao;
import com.javaegitimleri.petclinic.model.Address;
import com.javaegitimleri.petclinic.model.Image;
import com.javaegitimleri.petclinic.model.Owner;
import com.javaegitimleri.petclinic.model.OwnerWithCompositePK;
import com.javaegitimleri.petclinic.model.OwnerWithCompositePK.OwnerId;
import com.javaegitimleri.petclinic.service.PetClinicService;
import com.javaegitimleri.petclinic.model.Person;
import com.javaegitimleri.petclinic.model.Pet;
import com.javaegitimleri.petclinic.model.PetType;
import com.javaegitimleri.petclinic.model.Rating;
import com.javaegitimleri.petclinic.model.Visit;

public class HibernateTests {
	
	@Test
	public void testReportQueries() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		
		String queryString = "select p.name,p.birthDate from Pet p";
		
		List<Object[]> resultList = session.createQuery(queryString).getResultList();
		
		for(Object[] row:resultList) {
			String name = (String)row[0];
			Date birthDate = (Date)row[1];
			
			System.out.println("Pet with name :" + name + " and bidrth date :" + birthDate);
		}
	}
	
	@Test
	public void testJoins() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		
		String queryString = "select distinct o from Owner o left outer join o.pets p where p.name like :petName";
		
		Query<Owner> query = session.createQuery(queryString);
		
		query.setParameter("petName", "%");
		
//		List<Object[]> resultList = query.getResultList();
//		
//		for(Object[] row:resultList) {
//			Owner o = (Owner)row[0];
//			Pet p = (Pet)row[1];
//			System.out.println("Owner :" + o);
//			System.out.println("Pet :" + p);
//		}
		
		List<Owner> resultList = query.getResultList();
		
		resultList.forEach(System.out::println);
	}
	
	@Test
	public void testHql() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		
		String queryString = "select p from Pet p where p.name like ? or p.type.id = ?";
		
		Query<Pet> query = session.createQuery(queryString);
		
		query.setParameter(0, "K%");
		query.setParameter(1, 2L);
		
		List<Pet> resultList = query.getResultList();
		
		System.out.println("--- query executed ---");
		
		resultList.forEach(System.out::println);
	}
	
	@Test
	public void testAuditInterceptor() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		session.beginTransaction();
		
		Pet pet = new Pet("kedicik 5",new Date());
		
		session.persist(pet);
		
		Pet pet2 = session.get(Pet.class, 132L);
		
		pet2.setBirthDate(null);
		
		session.delete(session.load(Pet.class, 110L));
		
		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void testConcurrency() {
		Session session1 = HibernateConfig.getSessionFactory().openSession();
		session1.beginTransaction();
		
		Session session2 = HibernateConfig.getSessionFactory().openSession();
		session2.beginTransaction();
		
		Pet pet1 = session1.get(Pet.class, 1L);

		Pet pet2 = session2.get(Pet.class, 1L);
		
		pet1.setOwner(session1.load(Owner.class, 9L));
		
		pet2.setType(session2.load(PetType.class, 6L));
		
		session2.getTransaction().commit();
		
		System.out.println("--- after session 2 commit ---");
		
		session1.getTransaction().commit();
		
		System.out.println("--- after session 1 commit ---");
		
		session1.close();
		session2.close();

	}
	
	@Test
	public void testLayeredArchitecture() {
		PetClinicService pcs = new PetClinicService();
		OwnerDao ownerDao = new OwnerDao();
		ClinicDao clinicDao = new ClinicDao();
		SessionFactory sf = HibernateConfig.getSessionFactory();
		
		ownerDao.setSessionFactory(sf);
		clinicDao.setSessionFactory(sf);
		
		pcs.setOwnerDao(ownerDao);
		pcs.setClinicDao(clinicDao);
		
		Transaction tx = sf.getCurrentSession().beginTransaction();
		
		Owner owner1 = new Owner();
		owner1.setFirstName("A");
		owner1.setLastName("B");
		
		Owner owner2 = new Owner();
		owner2.setFirstName("C");
		owner2.setLastName("D");
		try {
			pcs.addNewOwners(1L, owner1,owner2);
			tx.commit();
		} catch(Exception ex) {
			tx.rollback();
			throw ex;
		}
	}
	
	@Test
	public void testContextualSession2() {
		Session session1 = HibernateConfig.getSessionFactory().getCurrentSession();
		Session session2 = HibernateConfig.getSessionFactory().getCurrentSession();
		
		System.out.println(session1 == session2);
		session1.beginTransaction().commit();
		session2 = HibernateConfig.getSessionFactory().getCurrentSession();
		System.out.println(session1 == session2);
		Session session3 = HibernateConfig.getSessionFactory().openSession();
		System.out.println(session2 == session3);
	}
	
	@Test
	public void testContextualSession() {
		Session session = HibernateConfig.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		PetType petType = session.get(PetType.class, 1L);
		
		petType.setName("xxx");
		
		tx.commit();
		//session.close();
		System.out.println("--- after tx commit ---");
		System.out.println("Session open :" + session.isOpen());
		
	}
	
	@Test
	public void testCascade() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Owner owner = new Owner();
		owner.setFirstName("Kenan");
		owner.setLastName("Sevindik");
		
		Pet pet = new Pet();
		pet.setName("my puppie");
		
		owner.getPets().add(pet);
		//pet.setOwner(owner);
		
		Visit visit = new Visit();
		visit.setVisitDescription("checkup");
		visit.setVisitDate(new Date());
		
		pet.getVisits().add(visit);
		
		session.persist(owner);
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void testRefresh() throws IOException {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		PetType pt = session.get(PetType.class, 1L);
		System.out.println("--- PetType loaded ---");
		pt.setName("xxx");
		
		System.out.println("--- waiting... ---");
		System.in.read();
		
		session.refresh(pt);
		
		System.out.println("--- after refresh ---");
		
		System.out.println(pt.getName());
	}
	
	@Test
	public void testFlushTxRelationship() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.setHibernateFlushMode(FlushMode.MANUAL);
		
		Owner owner = session.get(Owner.class, 7L);
		owner.setRating(null);
		
		session.persist(new Pet("my pet", new Date()));
		
		session.flush();
		System.out.println("--- after flush ---");
		//tx.rollback();
		tx.commit();
		session.close();
		System.out.println("--- after tx commit and session close ---");
	}
	
	@Test
	public void testDelete() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Visit visit = session.get(Visit.class, 2L);
		
		session.clear();
		
		session.delete(visit);
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void testHibernateInitialize() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Pet pet = session.load(Pet.class, 1L);
		
		System.out.println("--- before initialize ---");
		
		//Hibernate.initialize(pet);
		//Hibernate.initialize(pet.getImagesByFilePath());
		
		session.close();
		
		System.out.println("--- session closed ---");
		
		System.out.println(pet.getName());
		System.out.println(pet.getImagesByFilePath().size());
	}
	
	
	@Test
	public void testDetachedEntitiesAndLazy() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Pet pet = session.get(Pet.class, 1L);
		
		//session.close();
		//session.clear();
		session.evict(pet);
		
		System.out.println("--- after session evict ---");
		
		System.out.println("session open :" + session.isOpen());
		
		//session.update(pet);
		//session.lock(pet, LockMode.NONE);
		
		Pet pet2 = (Pet) session.merge(pet);
		
		Map<String, Image> imagesByFilePath = pet2.getImagesByFilePath();
		System.out.println(imagesByFilePath.getClass());
		System.out.println("image size :" + imagesByFilePath.size());
		
		tx.commit();
		session.close();
		
	}
	
	@Test
	public void testDetachedEntities() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Pet pet = session.get(Pet.class, 1L);
		
		tx.commit();
		session.close();
		
		
		session = HibernateConfig.getSessionFactory().openSession();
		tx = session.beginTransaction();
		
		pet.setBirthDate(new Date());
		
		//session.saveOrUpdate(pet);
		
		//session.lock(pet, LockMode.NONE);
		
		Pet pet2 = (Pet) session.merge(pet);
		
		pet2.setType(session.load(PetType.class, 5L));
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void testUpdate() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Pet pet = session.get(Pet.class, 1L);
		
		pet.setBirthDate(null);
		pet.setType(session.load(PetType.class, 3L));
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void testInsertWithMerge() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Pet pet = new Pet();
		pet.setName("kedicik 3");
		
		Pet pet2 = (Pet)session.merge(pet);
		
		System.out.println("--- after merge ---");
		
		pet.setBirthDate(new Date());
		
		System.out.println("pet id :" + pet.getId());
		System.out.println("pet 2 id :" + pet2.getId());
		System.out.println(pet == pet2);
		System.out.println("pet 2 birth date :" + pet2.getBirthDate());
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void testSave() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Pet pet = new Pet();
		pet.setName("kedicik 2");
		
		Serializable pk = session.save(pet);
		
		System.out.println("--- after save called ---");
		
		tx.commit();
		session.close();
		
		System.out.println(pk == pet.getId());
	}
	
	@Test
	public void testPersist() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Pet pet = new Pet();
		pet.setName("kedicik 1");
		
		session.persist(pet);
		System.out.println("--- after persist called ---");
		tx.commit();
		session.close();
	}
	
	@Test
	public void testStatistics() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(new Pet("kedicik", new Date()));
		
		session.get(Pet.class, 1L);
		
		session.flush();
		
		session.createQuery("select p.name from Pet p").getResultList();
		
		Statistics statistics = HibernateConfig.getSessionFactory().getStatistics();
		
		EntityStatistics entityStatistics = statistics.getEntityStatistics("com.javaegitimleri.petclinic.model.Pet");
		QueryStatistics queryStatistics = statistics.getQueryStatistics("select p.name from Pet p");
		
		System.out.println("load count :" + entityStatistics.getLoadCount());
		System.out.println("insert count :" + entityStatistics.getInsertCount());
		
		System.out.println("query exec count :" + queryStatistics.getExecutionCount());
		System.out.println("query avg exec time :" + queryStatistics.getExecutionAvgTime());
	}
	
	@Test
	public void testNaturalIdAccess() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		SimpleNaturalIdLoadAccess<Pet> simpleNaturalIdLoadAccess = session.bySimpleNaturalId(Pet.class);
		
		Pet pet = simpleNaturalIdLoadAccess.load("Maviþ");
		
		System.out.println("--- pet loaded ---");
		
		System.out.println(pet.getName());
		System.out.println(pet.getId());
		
		NaturalIdLoadAccess<Person> naturalIdLoadAccess = session.byNaturalId(Person.class);
		
		Person person = naturalIdLoadAccess.using("firstName", "Jale").using("lastName", "Cengiz").load();
		
		System.out.println("--- person loaded ---");
		System.out.println(person.getId());
		System.out.println(person.getClass());
	}
	
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
