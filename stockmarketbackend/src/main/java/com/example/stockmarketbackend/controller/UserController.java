package com.example.stockmarketbackend.controller;


import com.example.stockmarketbackend.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = { "/user"})
public class UserController {
    @GetMapping("/user")
    public String showUser(){
        return "hey";
    }
}
