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
public class AppController {

    private StockService stockService;
    private UserService userService;

    @Autowired
    public AppController(StockService stockService, UserService userService){
        this.stockService = stockService;
        this.userService = userService;
    }

    @RequestMapping(value = {"/index", "/"})
    public String index(Model model) throws IOException{
        model.addAttribute("userBalance", userService.getUserBalance());
        model.addAttribute("portfolio", userService.getUserPortfolio());
        model.addAttribute("userAccountWorth", userService.getUserAccountWorth());

        return "index";
    }

    @RequestMapping(value = {"/stock"})
    public String stock(HttpServletRequest request, Model model) throws IOException{
        String ticker = request.getParameter("ticker");

        try{
            model.addAttribute("stockPrice", stockService.getStockPrice(ticker));
            model.addAttribute("ticker", ticker);
            if(userService.getUserPortfolio().get(ticker) != null){
                model.addAttribute("numSharesOwned", userService.getUserPortfolio().get(ticker));
            }
            else{
                model.addAttribute("numSharesOwned", 0);
            }
        }
        catch(Exception e){
            return "invalidstock";
        }

        return "stock";
    }

    @RequestMapping(value = {"/trade"})
    public String trade(HttpServletRequest request, Model model) throws IOException{
        String ticker = request.getParameter("ticker");
        String purchased = request.getParameter("purchased");
        String sold = request.getParameter("sold");

        if(!(purchased == null) && !purchased.isEmpty()){
            stockService.makeTrade(ticker, Integer.parseInt(purchased), "01");
        }

        if(!(sold == null) && !sold.isEmpty()){
            stockService.makeTrade(ticker, -1*Integer.parseInt(sold), "01");
        }

        return "redirect:/index";
    }
}