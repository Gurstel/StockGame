package com.stockgame.stock.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Stock implements Comparable<Stock> {
    private String ticker;
    private int numStock;
    private ArrayList<Integer> purchaseAmounts = new ArrayList<Integer>();
    private ArrayList<Timestamp> purchaseTimes = new ArrayList<Timestamp>();



    public Stock(){

    }

    public Stock(@JsonProperty("ticker") String ticker, @JsonProperty("numStock") int numStock){
        this.ticker = ticker;
        this.numStock = numStock;
        purchaseAmounts.add(numStock);
        purchaseTimes.add(new Timestamp(System.currentTimeMillis()));
    }

    public String getTicker() {
        return this.ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getNumStock() {
        return this.numStock;
    }

    public void setNumStock(int numStock) {
        this.numStock = numStock;
    }

    public ArrayList<Timestamp> getPurchaseTimes() {
        return this.purchaseTimes;
    }

    public void setPurchaseTimes(ArrayList<Timestamp> purchaseTimes) {
        this.purchaseTimes = purchaseTimes;
    }

    public ArrayList<Integer> getPurchaseAmounts() {
        return this.purchaseAmounts;
    }

    public void setPurchaseAmounts(ArrayList<Integer> purchaseAmounts) {
        this.purchaseAmounts = purchaseAmounts;
    }

    public int compareTo(Stock o) {
        if(getTicker().equals(o.getTicker())){
            return 0;
        }
        else{
            return 1;
        }
    }

}
