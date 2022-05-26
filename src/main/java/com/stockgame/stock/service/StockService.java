package com.stockgame.stock.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Hashtable;

import com.stockgame.stock.model.DesiredStock;
import com.stockgame.stock.model.Trade;
import com.stockgame.stock.model.User;
import com.stockgame.stock.repository.UserRepository;

import org.springframework.stereotype.Service;

/*
StockService is where all of the stock business logic occurs. It is the intermediate
step between the Controller and database/models. StockService creates a 
database as private data to access users that need modification.

This class can get prices of stocks and make trades (while making sure the trade is
actually viable).
*/

@Service
public class StockService {
    
    private UserRepository userRepo;

    public StockService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public BigDecimal getStockPrice(String ticker) throws IOException{
        DesiredStock stock = new DesiredStock(ticker);
        return stock.getPrice();
    }

    public String makeTrade(String ticker, int numShares, String id) throws IOException{
        User user = userRepo.findUserByID("01");
        BigDecimal price = getStockPrice(ticker);
        Trade trade = new Trade(ticker, price, numShares);
        Double totalPrice = Double.parseDouble(trade.getTotalPrice().toString());
        if(numShares > 0){
            if(user.getCashBalance() < totalPrice){
                return "Invalid funds!";
            }
            user.executeTrade(trade);
            userRepo.save(user);
            return "Successfully bought " + ticker + ". New balance: " + user.getCashBalance();
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
            return "Successfully sold" + ticker + ". New balance: " + user.getCashBalance();
        }
    }
}
