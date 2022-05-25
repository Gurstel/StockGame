package com.stockgame.stock.model;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class DesiredStock {
    String ticker;
    Stock stock;


    public DesiredStock(String ticker) throws IOException{
        this.ticker = ticker;
        stock = YahooFinance.get(ticker);
    }


    public Stock getStock() {
        return this.stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public String getTicker() {
        return this.ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public BigDecimal getPrice() throws IOException {
        return stock.getQuote().getPrice();
    }

    public Calendar getTime() throws IOException {
        return stock.getQuote().getLastTradeTime();
    }
}
