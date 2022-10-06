package com.kk.bookstore.client;

import org.hibernate.Session;

import com.kk.bookstore.domain.Message;
import com.kk.bookstore.util.HibernateUtil;

public class HelloWorldClient01 {
	public static void main(String[] args) {
		
				Session session = HibernateUtil.getSessionFactory().openSession();
        		session.beginTransaction();
        
        		Message message = new Message( "Hello World with Hibernate and JPA Annotations" );
        
        		session.save(message);    
        
        		session.getTransaction().commit();
        		session.close();
	
	}
}