package com.kk.bookstore.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import com.kk.bookstore.domain.Actor;
import com.kk.bookstore.domain.Book;
import com.kk.bookstore.domain.Chapter;
import com.kk.bookstore.domain.Customer;
import com.kk.bookstore.domain.Guide;
import com.kk.bookstore.domain.Message;
import com.kk.bookstore.domain.Movie;
import com.kk.bookstore.domain.Passport;
import com.kk.bookstore.domain.Person;
import com.kk.bookstore.domain.Publisher;
import com.kk.bookstore.domain.Student;

public class HibernateUtil {
	
    private static final SessionFactory sessionFactory = buildSessionFactoryAnnotated();
    
    private static SessionFactory buildSessionFactoryAnnotated() {
    	
    	  // Hibernate 5.4 SessionFactory example without XML
    	  Map<String, String> settings = new HashMap<>();
    	  settings.put("connection.driver_class", "com.mysql.jdbc.Driver");
    	  settings.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
    	  settings.put("hibernate.connection.url", 
    	    "jdbc:mysql://localhost:3306/hello-world");
    	  settings.put("hibernate.connection.username", "root");
    	  settings.put("hibernate.connection.password", "techbankRootPsw");
    	  settings.put("hibernate.current_session_context_class", "thread");
    	  settings.put("hibernate.show_sql", "true");
    	  settings.put("hibernate.format_sql", "true");
    	  settings.put("hibernate.hbm2ddl.auto", "create");

    	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
    	                                    .applySettings(settings).build();

    	  MetadataSources metadataSources = new MetadataSources(serviceRegistry);
    	  metadataSources.addAnnotatedClass(Message.class);
    	  metadataSources.addAnnotatedClass(Book.class);
    	  metadataSources.addAnnotatedClass(Chapter.class);
    	  metadataSources.addAnnotatedClass(Publisher.class);
    	  metadataSources.addAnnotatedClass(Person.class);
    	  metadataSources.addAnnotatedClass(Student.class);
    	  metadataSources.addAnnotatedClass(Guide.class);
    	  metadataSources.addAnnotatedClass(Customer.class);
    	  metadataSources.addAnnotatedClass(Passport.class);
    	  metadataSources.addAnnotatedClass(Movie.class);
    	  metadataSources.addAnnotatedClass(Actor.class);
    	  Metadata metadata = metadataSources.buildMetadata();

    	  // here we build the SessionFactory (Hibernate 5.4)
    	  SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
    	  return sessionFactory;
    	
    }
    
    
    private static SessionFactory buildSessionFactory() {
        try {        	
            // for Hibernate 5.x users
            // Create the SessionFactory from hibernate.cfg.xml
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
            
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
	
}
