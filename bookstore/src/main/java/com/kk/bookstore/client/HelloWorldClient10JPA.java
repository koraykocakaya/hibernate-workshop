package com.kk.bookstore.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.kk.bookstore.domain.Message;

/**
 * 1. EntityManager yapisi direkt JPA yapisi aslinda
 *  Hibernate n tane JPA Providerindan bir tanesi (EclipseLink vs. de kullanilabilir) 
 * 2. EntityManager'i Hibernate'deki Session gibi dusunebiliriz
 * 3. JPA @Entity olanlari otomatik gorecektir ve tekrar classlari belirtmemize gerek olmayacaktir
 * 4. Burada yaratirken resources altinda META-INF/persistence.xml kontrol edilecektir ve 
 *   orada hello-world adindaki persistence-unit uzerinden yukleyecektir
 *   Bu sekilde farkli unitler yardimiyla n tane farkli DB kullanilabilecektir
 * @author korayk
 *
 */
public class HelloWorldClient10JPA {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hello-world");
		EntityManager em = factory.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			Message m = new Message("JPA Example");
			em.persist(m);
			
			txn.commit();
		} catch (Exception e) {
			if(txn != null)
				txn.rollback();
		} finally {
			if(em != null)
				em.close();
		}
		
	}
}