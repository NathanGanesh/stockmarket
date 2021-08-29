package com.example.stockmarketbackend.exceptions.domain.user;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
