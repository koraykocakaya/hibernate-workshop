package com.kk.bookstore.client;

import org.hibernate.Session;

import com.kk.bookstore.domain.Actor;
import com.kk.bookstore.domain.Movie;
import com.kk.bookstore.enums.MovieGenre;
import com.kk.bookstore.util.HibernateUtil;

/**
 * 1. ManyToOne icin ise ek olarak JoinTable tanimlayip ek tablo icin islemleri yapabildik
 * 2. Burada ek olarak Enum ekleyip @Enumerated ile isaretleyip veriyi DB'de nasil tutacagimizi belirtebildik
 * 	Default Ordinal tutulmakta.
 * @author korayk
 *
 */
public class HelloWorldClient07 {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Actor a1 = Actor.builder()
						.name("Koray")
						.build();
		
		Movie m1 = Movie.builder()
						.actor(a1)
						.genre(MovieGenre.SCI_FI)
						.name("Movie123")
						.year(1990)
						.build();
		
		session.persist(m1);
		

		session.getTransaction().commit();
		session.close();

	}
}