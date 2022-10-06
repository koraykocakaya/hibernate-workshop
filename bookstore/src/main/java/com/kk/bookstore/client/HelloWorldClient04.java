package com.kk.bookstore.client;

import org.hibernate.Session;

import com.kk.bookstore.domain.Address;
import com.kk.bookstore.domain.Person;
import com.kk.bookstore.util.HibernateUtil;

/**
 * 1.Burada Person icinde Address olmali ancak ayri bir tablo olmasini istemiyoruz (Composition)
 * 	Yani Person olmadan Address ile ilgili bir isimiz yoksa bu sekilde kurgulamamiz gerekmekte
 * 2. Bunun icin Address'i @Embeddable olarak isaretledik ve Person icinde kullanirken de @Embedded ile alabildik.
 * 3. Ek olarak @AttributeOverrides annotationu yardimiyla Embedded kolon isimlerini guncelleyebildik
 *  Burada genel olarak direkt elle class icine gomebiliriz ama kendi yapimiz icin bu sekilde daha uygun olacaktir
 * 
 * @author korayk
 *
 */
public class HelloWorldClient04 {
	public static void main(String[] args) {
		Session sessionOld = HibernateUtil.getSessionFactory().openSession();
		sessionOld.close();
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Address a1 = Address.builder()
							.city("Ankara")
							.town("Cankaya")
							.build();
		
		Person p1 = Person.builder()
						  .address(a1)
						  .name("Koray")
						  .build();
		
		session.save(p1);
		session.getTransaction().commit();
		session.close();

	}
}