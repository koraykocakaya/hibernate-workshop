package com.kk.bookstore.client;

import java.util.Arrays;

import org.hibernate.Session;

import com.kk.bookstore.domain.Player;
import com.kk.bookstore.util.HibernateUtil;

/**
 * 1. Direkt Value Typeinda bir collection tutmak istersek de @ElementCollection ile isaretlemek gerekmektedir
 * 2. Ek bilgi: businee olarak id olan alani ID yapmak mantikli olmayabilir
 * 	Yani default bizdeki id gibi bir deger vermek tckn, isbn vs. yapmaktan daha saglikli olacaktir
 * 
 * @author korayk
 *
 */
public class HelloWorldClient09ValueTypeCollection {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Player player = Player.builder()
							  .name("Koray")
							  .games(Arrays.asList("Fifa", "COD"))
							  .build();
		
		session.persist(player);
		session.getTransaction().commit();
		session.close();

	}
}