package com.yashdev.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement//ye keval main class me lagana hota hai, isse hume transaction management ka support milta hai, jisse hum apne service layer me transactions ko manage kar sakte hain. Iska use karne se hum apne database operations ko atomic bana sakte hain,
// yani ki agar koi operation fail ho jata hai to puri transaction rollback ho jayegi
public class JournalApp {

	public static void main(String[] args) {
		SpringApplication.run(JournalApp.class, args);
	}
	@Bean// This annotation indicates that this method produces a bean to be managed by the Spring container. In this case, it creates a PlatformTransactionManager bean that is used for managing transactions in MongoDB.
	//This method creates a MongoTransactionManager bean that is used for managing transactions in MongoDB. The MongoTransactionManager is a specific implementation of the PlatformTransactionManager interface that is designed to work with MongoDB databases.
	// By defining this bean, we enable transaction management for our MongoDB operations, allowing us to ensure data integrity and consistency when performing multiple database operations within a single transaction.
	public PlatformTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}

}
