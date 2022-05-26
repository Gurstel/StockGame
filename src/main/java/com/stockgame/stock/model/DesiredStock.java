package com.stockgame.stock.model;

import java.io.IOException;
import java.math.BigDecimal;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class DesiredStock {

    String ticker;
    
    Stock stock;


    public DesiredStock(String ticker) throws IOException{
        this.ticker = ticker;
        stock = YahooFinance.get(ticker);
    }

    public String getTicker() {
        return this.ticker;
    }

    public BigDecimal getPrice() throws IOException {
        return stock.getQuote().getPrice();
    }
}
