package com.stockgame.stock.service;

import java.util.Hashtable;

import com.stockgame.stock.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private UserRepository userRepo;

    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public Hashtable<String,Integer> getUserPortfolio(){
        return userRepo.findUserByID("01").getPortfolioStocks();
    }

    public String createUser(String name){
        return "hello";
    }

    public Double getUserBalance(){
        return userRepo.findUserByID("01").getCashBalance();
    }


}
