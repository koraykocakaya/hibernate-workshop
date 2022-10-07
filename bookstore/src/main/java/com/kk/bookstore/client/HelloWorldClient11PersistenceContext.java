package com.kk.bookstore.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.kk.bookstore.domain.Message;

/**
 * 1. Bu ornekte oncelikle kaydi cektik bu durumda persisted stateinde Persistence context icinde
 * 2. Sonrasinda detach dedigimizde Detach state aldik, bu durumda aksiyon alamayiz
 * 3. Sonrasinda merge ile setledigimizde tekrar Persistence Context almis olduk
 * 4. Remove calistirdiktan sonra persist calistirdigimiz icin remove calistirmadi ve sadece text verisini guncellemis oldu
 *  Text degeri degismemis olsaydi (Automatic Dirty Checking) update islemi calismayacakti
 * 5. Ayni kaydi 2 defa cektigimizde 2 defa DB'ye gitmeyecektir, Persistence Context'te varsa oradan getirecektir,
 *  Burada Persistence Context First Level Cache olarak kullanilmaktadir, gereksiz tekrar sorgu yapilmamaktadir
 *  Kayit DB'de degisse bile aslinda ayni kaydi getirecektir cacheten (Repeatable Read)
 *  refresh metoduyla guncellenmeleri saglanabilir
 * 6. em2.detach(persistedMsg); dedigimizde persistedMsg2 de aslinda detached olmus olacaktir,
 *  Sonrasinda merge'u persistedMsg setledigimiz icin persistedMsg2 detached kalacaktir, onunla islem yapmak istersek hata alacaktir
 * 7. Persisted objeler manager disinda yine Detached durumunda olacaktir, buna gore yonetilmesi gerekecektir
 * 8. em2.clear() cagirdigimizda bagli tum objeler detached haline gelecektir
 *  
 * @author korayk
 *
 */
public class HelloWorldClient11PersistenceContext {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hello-world");
		
		EntityManager em2 = factory.createEntityManager();
		EntityTransaction txn2 = em2.getTransaction();
		
		try {
			txn2.begin();
			
			Message persistedMsg = em2.find(Message.class, 1L);
			Message persistedMsg2 = em2.find(Message.class, 1L);
			
			em2.detach(persistedMsg);
			persistedMsg = em2.merge(persistedMsg);
			em2.remove(persistedMsg);
			
			persistedMsg.setText("8-Updated JPA example");
			
			// Eski veriyi basar detached oldugu icin
			System.out.println(persistedMsg2.getText());
			em2.persist(persistedMsg);
			txn2.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(txn2 != null)
				txn2.rollback();
		} finally {
			if(em2 != null)
				em2.close();
		}
		
		
	}
}