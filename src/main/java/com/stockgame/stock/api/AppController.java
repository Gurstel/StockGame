package com.stockgame.stock.api;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.stockgame.stock.service.StockService;
import com.stockgame.stock.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
Controller for displaying HTML files based on client-side operations, including form
submissions, as well as simply taking them to the home page
*/
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
        model.addAttribute("userAccountWorth", userService.getUserAccountWorth());
        model.addAttribute("portfolioData", userService.getPortfolioData());

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
            return "invalid-stock";
        }

        return "stock";
    }

    @RequestMapping(value = {"/trade"})
    public String trade(HttpServletRequest request, Model model) throws IOException{
        String ticker = request.getParameter("ticker");
        String purchased = request.getParameter("purchased");
        String sold = request.getParameter("sold");
        String purchaseCheck = "";
        String sellCheck = "";

        model.addAttribute("ticker", ticker);

        if(!(purchased == null) && !purchased.isEmpty()){
            try{
                purchaseCheck = stockService.makeTrade(ticker, Integer.parseInt(purchased), "01");
            }
            catch(Exception e){
                model.addAttribute("error", "You must buy whole stock. Please make sure to type a whole number.");
                return "trade-error";
            }
        }

        if(!(sold == null) && !sold.isEmpty()){
            try{
                sellCheck = stockService.makeTrade(ticker, -1*Integer.parseInt(sold), "01");
            }
            catch(Exception e){
                model.addAttribute("error", "You must sell whole stock. Please make sure to type a whole number.");
                return "trade-error";
            }
        }

        if(purchaseCheck == "Invalid funds!"){
            model.addAttribute("error", "Invalid funds!");
            return "trade-error";
        }

        if(sellCheck == "Not enough shares owned!"){
            model.addAttribute("error", "Not enough shares owned!");
            return "trade-error";
        }

        return "redirect:/index";
    }
}