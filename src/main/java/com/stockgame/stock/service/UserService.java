package com.stockgame.stock.service;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Set;

import com.stockgame.stock.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private UserRepository userRepo;

    private StockService stockService;

    public UserService(UserRepository userRepo, StockService stockService){
        this.userRepo = userRepo;
        this.stockService = stockService;
    }

    public Hashtable<String,Integer> getUserPortfolio(){
        return userRepo.findUserByID("01").getPortfolioStocks();
    }

    public Double getUserAccountWorth() throws IOException {
        Double userAccountWorth = 0.0;
        Set<String> ownedStocks = getUserPortfolio().keySet();
        for(String stock: ownedStocks){
            userAccountWorth += Double.parseDouble(stockService.getStockPrice(stock)) * getUserPortfolio().get(stock);
        }
        userAccountWorth += getUserBalance();
        return userAccountWorth;
    }

    public String createUser(String name){
        return "hello";
    }

    public Double getUserBalance(){
        return userRepo.findUserByID("01").getCashBalance();
    }


}
