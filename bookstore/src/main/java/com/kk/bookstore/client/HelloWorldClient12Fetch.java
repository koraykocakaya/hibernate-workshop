package com.kk.bookstore.client;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.kk.bookstore.domain.Guide;
import com.kk.bookstore.domain.Student;

/**
 * 1.  
 * @author korayk
 *
 */
public class HelloWorldClient12Fetch {
	
	static Guide g1 = null;
	
	public static void main(String[] args) {
		populateRecs();
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hello-world");
		
		EntityManager em = factory.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			Guide guide = em.find(Guide.class, g1.getId());
			System.out.println(guide.getStudents().size());
			
			txn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(txn != null)
				txn.rollback();
		} finally {
			if(em != null)
				em.close();
		}
		
		
	}
	
	private static void populateRecs() {
		
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hello-world");
		
		EntityManager em = factory.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			Student s1 = Student.builder()
								.name("s1")
								.build();
			
			Student s2 = Student.builder()
								.name("s2")
								.build();
			
			g1 = Guide.builder()
							.name("Guide1")
							.students(Arrays.asList(s1,s2))
							.build();
							
			em.persist(g1);
			
			txn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(txn != null)
				txn.rollback();
		} finally {
			if(em != null)
				em.close();
		}
	}
}