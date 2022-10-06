package com.kk.bookstore.client;

import org.hibernate.Session;

import com.kk.bookstore.domain.Message;
import com.kk.bookstore.util.HibernateUtil;

/**
 * flush metodu transaction commitlenmeden dirty check varsa onun DB'ye yazilmasini saglamaktadir
 * @author korayk
 *
 */
public class HelloWorldClient08 {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
			
		Message m1 = new Message("Text 123");
		session.persist(m1);
		

		session.getTransaction().commit();
		session.close();
		
		// new Session
		Session sessionNew = HibernateUtil.getSessionFactory().openSession();
		sessionNew.beginTransaction();
		
		Message message = sessionNew.get(Message.class, 1L);
		message = (Message)sessionNew.merge(message);
		sessionNew.detach(message);
		sessionNew.delete(message);
		System.out.println(message.getText());
		System.out.println("Removed");
		sessionNew.getTransaction().commit();
		sessionNew.close();

	}
}