package com.stockgame.stock.api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.stockgame.stock.service.StockService;
import com.stockgame.stock.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    private StockService stockService;
    private UserService userService;

    @Autowired
    public IndexController(StockService stockService, UserService userService){
        this.stockService = stockService;
        this.userService = userService;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexform(){
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String index(HttpServletRequest request, Model model) throws IOException{
        String ticker = request.getParameter("ticker");
        String numSharesBought = request.getParameter("numSharesBought");
        String numSharesSold = request.getParameter("numSharesSold");
        String numSharesOwned;
        
        if(ticker == null){
            ticker = "AAPL";
        }

        if(numSharesBought != null && numSharesBought != ""){
            stockService.makeTrade(ticker, Integer.parseInt(numSharesBought), "01");
        }

        if(numSharesSold != null && numSharesSold != ""){
            stockService.makeTrade(ticker, -1*Integer.parseInt(numSharesSold), "01");
        }

        if(userService.getUserPortfolio().get(ticker) == null){
            numSharesOwned = "0";
        }
        else{
            numSharesOwned = "" + userService.getUserPortfolio().get(ticker);
        }

        model.addAttribute("stockName", ticker);
        model.addAttribute("stockPrice", stockService.getStockPrice(ticker));
        model.addAttribute("userBalance", userService.getUserBalance());
        model.addAttribute("numSharesOwned", numSharesOwned);
        model.addAttribute("portfolio", userService.getUserPortfolio());
        return "index";
    }
}