package com.stockgame.stock.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

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

    public HashMap<String, HashMap<String, Object>> getPortfolioData() throws IOException{
        HashMap<String, HashMap<String, Object>> portfolioData = new HashMap<String, HashMap<String, Object>>();
        Hashtable<String, Integer> userPortfolio = getUserPortfolio();
        Set<String> ownedStocks = userPortfolio.keySet();
        for(String stock: ownedStocks){
            Double stockPrice = Double.parseDouble(stockService.getStockPrice(stock).toString());
            HashMap<String, Object> stockData = new HashMap<String, Object>();
            stockData.put("numShares", userPortfolio.get(stock));
            stockData.put("currentPrice", stockPrice);
            stockData.put("totalEquity", userPortfolio.get(stock)*stockPrice);
            portfolioData.put(stock, stockData);
        }
        return portfolioData;
    }

    public Double getUserAccountWorth() throws IOException {
        HashMap<String, HashMap<String, Object>> portfolioData = getPortfolioData();
        Double userAccountWorth = 0.0;

        for (Entry<String, HashMap<String, Object>> entry : portfolioData.entrySet()) {
            Map<String, Object> childMap = entry.getValue();
            Double stockEquity = (Double) childMap.get("totalEquity");
            userAccountWorth += stockEquity;
        }
        userAccountWorth += getUserBalance();
        return userAccountWorth;
    }

    public Double getUserBalance(){
        return userRepo.findUserByID("01").getCashBalance();
    }


}
