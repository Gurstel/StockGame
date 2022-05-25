package com.stockgame.stock.model;

import java.sql.Timestamp;

public class Trade {
    private String userID;
    private String ticker;
    private Timestamp time;
    private Double pricePerShare;
    private int sharesTraded;

    public Trade(String userID, String ticker, Timestamp time, Double pricePerShare, int sharesTraded){
        this.userID = userID;
        this.ticker = ticker;
        this.time = time;
        this.pricePerShare = pricePerShare;
        this.sharesTraded = sharesTraded;
    }


    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTicker() {
        return this.ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Timestamp getTime() {
        return this.time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Double getPricePerShare() {
        return this.pricePerShare;
    }

    public void setPricePerShare(Double pricePerShare) {
        this.pricePerShare = pricePerShare;
    }

    public int getSharesTraded() {
        return this.sharesTraded;
    }

    public void setSharesTraded(int sharesTraded) {
        this.sharesTraded = sharesTraded;
    }

    public Double getTotalPrice(){
        return pricePerShare*Math.abs(sharesTraded);
    }


}
