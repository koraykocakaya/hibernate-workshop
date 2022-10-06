package com.kk.bookstore.client;

import org.hibernate.Session;

import com.kk.bookstore.domain.Message;
import com.kk.bookstore.util.HibernateUtil;

/**
 * 1. Update cagirmasak bile session icinde oldugu icin update calistiracaktir
 * 2. 
 * @author korayk
 *
 */
public class HelloWorldClient02 {
	public static void main(String[] args) {
		
				Session session = HibernateUtil.getSessionFactory().openSession();
        		session.beginTransaction();
        
        		Message m1 = session.get(Message.class, 1L);
        		m1.setText("Text1235");
        		
        		session.getTransaction().commit();
        		session.close();
        		m1.setText("asdasd");
        		
        		Session session2 = HibernateUtil.getSessionFactory().openSession();
        		session2.beginTransaction();
        		
        		// Ayni id ile bir daha cekersek hata atacaktir, ancak merge dersek m2 de guncellenecegi icin sorun olmayacaktir
        		Message m2 = session2.get(Message.class, 1L);
        		session2.merge(m1);
        		
        		
        		session2.getTransaction().commit();
        		session2.close();
        		
        		System.out.println(m2.getText());
	
	}
}