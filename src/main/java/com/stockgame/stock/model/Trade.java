package com.stockgame.stock.model;

import java.math.BigDecimal;

public class Trade {
    private String ticker;
    private BigDecimal pricePerShare;
    private int sharesTraded;

    public Trade(String ticker, BigDecimal pricePerShare, int sharesTraded){
        this.ticker = ticker;
        this.pricePerShare = pricePerShare;
        this.sharesTraded = sharesTraded;
    }

    public String getTicker() {
        return this.ticker;
    }

    public int getSharesTraded() {
        return this.sharesTraded;
    }
    
    public BigDecimal getTotalPrice(){
        return pricePerShare.multiply(new BigDecimal(Math.abs(sharesTraded)));
    }
}
