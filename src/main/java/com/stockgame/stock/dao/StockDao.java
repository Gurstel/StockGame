package com.stockgame.stock.dao;
import java.util.ArrayList;
import com.stockgame.stock.model.Stock;

public interface StockDao {
    
    int buyStock(Stock stock);

    int sellStock(Stock stock);

    ArrayList<Stock> seeStocks();

}
