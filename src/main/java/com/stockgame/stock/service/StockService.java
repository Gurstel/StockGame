package com.stockgame.stock.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Hashtable;

import com.stockgame.stock.model.DesiredStock;
import com.stockgame.stock.model.Trade;
import com.stockgame.stock.model.User;
import com.stockgame.stock.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class StockService {
    
    private UserRepository userRepo;

    public StockService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public String getStockPrice(String ticker) throws IOException{
        DesiredStock stock = new DesiredStock(ticker);
        
        return stock.getPrice().toString();
        
    }

    public String makeTrade(String ticker, int numShares, String id) throws IOException{
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        User user = userRepo.findUserByID("01");
        Double price = Double.parseDouble(getStockPrice(ticker));
        Trade trade = new Trade(user.getID(), ticker, time, price, numShares);
        Double totalPrice = trade.getTotalPrice();
        if(numShares > 0){
            if(user.getCashBalance() < totalPrice){
                return "Invalid funds!";
            }
            //This will also add the trade to database
            user.executeTrade(trade);
            userRepo.save(user);
            return "Successfully bought " + ticker + " @ " + time +". New balance: " + user.getCashBalance();
        }
        else{
            Hashtable<String, Integer> portfolio = user.getPortfolioStocks();
            System.out.println(portfolio.get(ticker));
            System.out.println(Math.abs(numShares));
            if(!portfolio.containsKey(ticker) || portfolio.get(ticker) < Math.abs(numShares)){
                return "Not enough shares owned!";
            }
            user.executeTrade(trade);
            userRepo.save(user);
            return "Successfully sold" + ticker + " @ " + time +". New balance: " + user.getCashBalance();
        }
    }

    public Hashtable<String, Integer> getPortfolio(String id){
        return userRepo.findUserByID(id).getPortfolioStocks();
    }
}
