package com.yashdev.journalApp;

import org.springframework.aop.TargetSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement//ye keval main class me lagana hota hai, isse hume transaction management ka support milta hai, jisse hum apne service layer me transactions ko manage kar sakte hain. Iska use karne se hum apne database operations ko atomic bana sakte hain,
// yani ki agar koi operation fail ho jata hai to puri transaction rollback ho jayegi
public class JournalApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JournalApp.class, args);
		System.out.println(context.getEnvironment());


	}
	@Bean// This annotation indicates that this method produces a bean to be managed by the Spring container. In this case, it creates a PlatformTransactionManager bean that is used for managing transactions in MongoDB.
	//This method creates a MongoTransactionManager bean that is used for managing transactions in MongoDB. The MongoTransactionManager is a specific implementation of the PlatformTransactionManager interface that is designed to work with MongoDB databases.
	// By defining this bean, we enable transaction management for our MongoDB operations, allowing us to ensure data integrity and consistency when performing multiple database operations within a single transaction.
	public PlatformTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}

	//RestTemplate is a class provided by Spring Framework that allows us to make HTTP requests to external APIs and consume their responses. It provides a convenient way to interact with RESTful web services and handle the communication between our application and external APIs.
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
