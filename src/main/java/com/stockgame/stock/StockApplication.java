package com.stockgame.stock;

import com.stockgame.stock.model.User;
import com.stockgame.stock.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

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
		User user = userRepo.findUserByID("01");
		System.out.print(user.getID());
		
	}
}
