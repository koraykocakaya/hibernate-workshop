package com.kk.bookstore.client;

import org.hibernate.Session;

import com.kk.bookstore.domain.Message;
import com.kk.bookstore.util.HibernateUtil;

/**
 * 1. Update cagirmasak bile session icinde oldugu icin update calistiracaktir
 * 2. Session kapandiktan sonra Detached state olacaktir, 
 * 	ancak devaminda merge dedigimiz icin tekrar Transient state'e alinacagi icin m1 degerini guncelleyecektir
 * @author korayk
 *
 */
public class HelloWorldClient03 {
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
        		session2.merge(m1);
        		
        		session2.getTransaction().commit();
        		session2.close();
        		
	}
}