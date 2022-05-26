package com.stockgame.stock.model;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

/*
Model for a stock. Imports yahooFinance in order to get real time stock data.
Stock is comprised of a ticker and yahooFinanace stock.
*/
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
        return stock.getQuote().getPrice().setScale(2, RoundingMode.HALF_UP);
    }
}
