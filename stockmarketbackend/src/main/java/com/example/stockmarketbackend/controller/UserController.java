package com.example.stockmarketbackend.controller;

import com.example.stockmarketbackend.domain.User;
import com.example.stockmarketbackend.dto.UserRegisterDto;
import com.example.stockmarketbackend.exceptions.domain.user.*;
import com.example.stockmarketbackend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = {  "/api/user"})
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRegisterDto user) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException, RegisterException, PasswordNotFoundException {
        User newUser = userService.register(user);
        return new ResponseEntity<>(newUser, OK);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }



}
