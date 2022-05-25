package com.stockgame.stock.repository;
import com.stockgame.stock.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {
    
    @Query("{ID:'?0'}")
    User findUserByID(String id);

}