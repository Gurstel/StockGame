package com.stockgame.stock.dao;
import com.stockgame.stock.model.Stock;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.sql.Timestamp;

@Repository("StockDao")
public class StockDataAccessService implements StockDao {
    
    private static ArrayList<Stock> DB = new ArrayList<>();

    public int buyStock(Stock stock){
        for(Stock s: DB){
            if(s.compareTo(stock) == 0){
                s.setNumStock(s.getNumStock()+stock.getNumStock());
                s.getPurchaseAmounts().add(stock.getNumStock());
                s.getPurchaseTimes().add(new Timestamp(System.currentTimeMillis()));
                return 1;
            }
        }
        DB.add(new Stock(stock.getTicker(), stock.getNumStock()));
        return 1;
    }

    public int sellStock(Stock stock){
        for(Stock s: DB){
            if(s.compareTo(stock) == 0 && s.getNumStock() >= stock.getNumStock()){
                s.setNumStock(s.getNumStock()-stock.getNumStock()); 
            }
        }
        return 1;
    }

    public ArrayList<Stock> seeStocks(){
        return DB;
    }

}
