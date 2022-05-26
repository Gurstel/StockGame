package com.stockgame.stock.repository;
import com.stockgame.stock.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/*
This interface is the repository we use to get a User. For this application
all we need is to access a user by their id.
*/
public interface UserRepository extends MongoRepository<User, String> {
    
    @Query("{ID:'?0'}")
    User findUserByID(String id);

}