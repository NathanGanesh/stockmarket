package com.example.stockmarketbackend.exceptions.domain.user;

public class PasswordNotFoundException extends Exception {
    public PasswordNotFoundException(String message) {
        super(message);
    }
}