package com.stockgame.stock.service;

import com.stockgame.stock.dao.StockDao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import com.stockgame.stock.model.Stock;

@Service
public class StockService {
    
    private StockDao stockDao;

    public StockService(@Qualifier("StockDao") StockDao stockDao){
        this.stockDao = stockDao;
    }

    public int buyStock(Stock stock){
        return stockDao.buyStock(stock);
    }

    public int sellStock(Stock stock){
        return stockDao.sellStock(stock);
    }

    public ArrayList<Stock> seeStocks(){
        return stockDao.seeStocks();
    }
}
