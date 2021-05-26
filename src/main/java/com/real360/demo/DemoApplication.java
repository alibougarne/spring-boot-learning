package com.real360.demo;

import db.seeds.UserDataSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		// UserDataSeeder userDataSeeder = new UserDataSeeder();
		// userDataSeeder.seedData();
		// userDataSeeder.seedData();
	}


}
