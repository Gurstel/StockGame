package com.stockgame.stock;

import com.stockgame.stock.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/*
This is how SpringFramework opens a port for the web-application to be hosted on.
Here we start the application by going to the user with ID 01 because an
authentification/multiple user application has not been implemented.
*/

@SpringBootApplication
@EnableMongoRepositories
public class StockApplication implements CommandLineRunner{
    
    @Autowired
    UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepo.findUserByID("01");
		
	}
}
