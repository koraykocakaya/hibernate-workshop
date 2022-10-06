package com.kk.bookstore.client;

import org.hibernate.Session;

import com.kk.bookstore.domain.Guide;
import com.kk.bookstore.domain.Student;
import com.kk.bookstore.util.HibernateUtil;

/**
 * 1. Bir Guide'a bagli n tane Student olacagi icin @ManyToOne kullanildi
 * 2. Burada joinColumn ile belirttigimiz kolon foreign key icin olan kolonun adi olacaktir.
 *  Buradaki ornek aslinda Aggregation yani classlar tablolara maplenecek sekilde kurgulandi
 * 3. Save durumunda 2 konu kritik, eger Cascade vermezsek ve Guide'i save almadan Student savelemeye calisirsak
 *  3.1 Bu durumda Student'ta Guide setlemissek hata atacaktir.
 *  3.2 Guide'ı setlememissek kolonu null yapip save islemini yapacaktir
 * 4. Benzer sekilde Cascade Delete kullanirsak da save aldigi gibi ilgili kaydi silecektir
 * 5. OneToMany'de de PErsist kullanarak ilgili kaydi DB'ye atmasini saglayabiliriz,
 * 	Ancak update etmek istedigimizde bu sekilde calismayacaktir, cunku relationship owneri aslinda Student
 *  Asagidaki ornekte de Guide'in Student listesine ekledik ancak Student Guide'i guncellenmeyecektir, 
 *  bunun icin addStudent gibi bir metot yapip Guide setlememiz gerekecektir
 * 6. OneToMany olan relationshipin owneri olmamali, bu sorun yaratacaktir.
 *  Otomatik olarak bir ara tablo daha tutucaktir (ManyToMany gibi), bu da costu artiracaktir
 * 7. session3'te kullanildigi uzere Sutdent sildigimizde Cascade'den dolayi Guide'i da silmesini bekledik
 * 	Ancak Guide'in bagli oldugu baska bir Student daha oldugu icin sorun olacakti, 
 *  Bunun icin Guide'da orphanRemoval=true vererek diger Student'in da silinmesini sagladik
 * @author korayk
 *
 */
public class HelloWorldClient05 {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Guide guide = Guide.builder()
						   .name("Guide 123")
						   .build();
		
		Student student = Student.builder()
								 .name("Koray")
								 .no(1234)
								 .guide(guide)
								 .build();
		
		Student student2 = Student.builder()
				 .name("Koray")
				 .no(12345)
				 .build();
		
		session.persist(student);
		session.persist(student2);
						
		session.getTransaction().commit();
		session.close();
		

		
		// new session
		Session session2 = HibernateUtil.getSessionFactory().openSession();
		session2.beginTransaction();
		
		
		Guide guide2 = session2.get(Guide.class, guide.getId());
		Student studentPersist = session2.get(Student.class, student2.getId());
		
		//guide2.getStudents().add(studentPersist); kayit guncellenmeyecektir
		guide2.addStudent(studentPersist);
		
		session2.persist(guide2);
		
		session2.getTransaction().commit();
		session2.close();

		
		
		Session session3 = HibernateUtil.getSessionFactory().openSession();
		session3.beginTransaction();
		
		
		Student student1 = session3.get(Student.class, student.getId());
		
		session3.remove(student1);
	
		session3.getTransaction().commit();
		session3.close();
	}
}