package com.stockgame.stock.model;
import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Users")
public class User {

    @Id
    private String ID;

    private String name;
    private Double cashBalance;
    private List<String> tradeHistory;
    private Hashtable<String, Integer> portfolioStocks;

    public User(String ID, String name, Double cashBalance){
        this.ID = ID;
        this.name = name;
        this.cashBalance = cashBalance;
        this.tradeHistory = new ArrayList<String>();
        this.portfolioStocks = new Hashtable<String, Integer>();
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hashtable<String, Integer> getPortfolioStocks(){
        return portfolioStocks;
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String id) {
        this.ID = id;
    }

    public Double getCashBalance(){
        return cashBalance;
    }

    public void setCashBalance(Double balance){
        this.cashBalance = balance;
    }

    public void executeTrade(Trade trade){
        String ticker = trade.getTicker();
        int sharesTraded = trade.getSharesTraded();
        Integer currentNumShares = portfolioStocks.get(trade.getTicker());
        int newNumShares;

        if(currentNumShares != null){
            newNumShares = currentNumShares + sharesTraded;
        }
        else{
            newNumShares = sharesTraded;
        }

        Double totalPriceOfTrade = trade.getTotalPrice();

        if(sharesTraded > 0){
            if(currentNumShares != null){
                portfolioStocks.replace(ticker, newNumShares);
            }
            else{
                portfolioStocks.put(ticker, sharesTraded);
            }
            setCashBalance(getCashBalance() - totalPriceOfTrade);
            tradeHistory.add("Bought " + sharesTraded + " of $" + ticker + " shares.");
        }
        else{
            portfolioStocks.replace(ticker, newNumShares);

            if(newNumShares == 0){
                portfolioStocks.remove(ticker);
            }

            setCashBalance(getCashBalance() + totalPriceOfTrade);
            tradeHistory.add("Sold " + trade.getSharesTraded() + " of $" + trade.getTicker() + " shares.");
        }
    }
}
