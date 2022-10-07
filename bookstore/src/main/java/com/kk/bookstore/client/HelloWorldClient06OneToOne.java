package com.kk.bookstore.client;

import org.hibernate.Session;

import com.kk.bookstore.domain.Customer;
import com.kk.bookstore.domain.Passport;
import com.kk.bookstore.util.HibernateUtil;

/**
 * 1. OneToOne da aslinda ManyToOne gibi
 * 2. Buradaki ornekte bagli oldugu kayittaki ID'yi buradaki key yapmak istedigimiz icin
 * 	Burada Customer'da Passport'u @MapsID ile isaretledik
 *  Boylece ayrica bir ID kolonu olusturmadan passport_id primary key olarak setlendi
 *  Yani foreign key ayni zamanda primary key de olmus oldu
 * @author korayk
 *
 */
public class HelloWorldClient06OneToOne {
	public static void main(String[] args) {
		Session sessionOld = HibernateUtil.getSessionFactory().openSession();
		sessionOld.close();
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Passport p1 = Passport.builder()
							  .no("132456789")
							  .build();
		
		Customer c1 = Customer.builder()
							  .name("Koray")
							  .passport(p1)
							  .build();
		
		session.persist(c1);
		
		session.getTransaction().commit();
		session.close();

	}
}