package com.stockgame.stock.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stockgame.stock.service.StockService;
import com.stockgame.stock.model.Stock;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.ArrayList;

//hellos
@Controller
public class StockController {

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("name", "ahmed123456");
        model.addAttribute("age", "22");
        return "index";
    }

}
