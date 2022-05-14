package com.stockgame.stock.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stockgame.stock.service.StockService;
import com.stockgame.stock.model.Stock;

import java.util.ArrayList;

//hellos
@RequestMapping("/stock")
@RestController
public class StockController {
    
    private StockService stockService;

    @Autowired
    public StockController(StockService stockService){
        this.stockService = stockService;
    }

    @PostMapping("/buy")
    public void buyStock(@RequestBody Stock stock){
        stockService.buyStock(stock);
    }

    @PostMapping("/sell")
    public void sellStock(@RequestBody Stock stock){
        stockService.sellStock(stock);
    }

    @GetMapping
    public ArrayList<Stock> seeStocks(){
        return stockService.seeStocks();
    }

}
