package com.stockgame.stock.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stockgame.stock.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.Hashtable;



@RestController
@Controller
@RequestMapping("/user")
public class UserController {
    
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/get")
    public Hashtable<String, Integer> getUser(){
        return userService.getUserPortfolio();
    }

    @PostMapping("/create")
    public String createUser(@RequestBody String name) throws IOException{
       return userService.createUser(name);
    }
}
