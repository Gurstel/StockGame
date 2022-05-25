package com.stockgame.stock.api;

import java.io.IOException;
import java.util.Hashtable;

import com.stockgame.stock.service.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {
    
    private StockService stockService;

    @Autowired
    public StockController(StockService stockService){
        this.stockService = stockService;
    }

    @RequestMapping(value = "/{ticker}/price", method = RequestMethod.GET)
    public String getStockPrice(@PathVariable("ticker") String ticker) throws IOException{
       return stockService.getStockPrice(ticker);
    }

    @RequestMapping(value = "/trade/{id}/{ticker}/{numShares}", method = RequestMethod.GET)
    public String makeTrade(
        @PathVariable("id") String id,
        @PathVariable("ticker") String ticker,
        @PathVariable("numShares") int numShares
        ) throws IOException{

       return stockService.makeTrade(ticker, numShares, id);
    }

    @RequestMapping(value = "/portfolio", method = RequestMethod.GET)
    public Hashtable<String, Integer> getPortfolio(@RequestParam("id") String id) throws IOException{
       return stockService.getPortfolio(id);
    }

}
