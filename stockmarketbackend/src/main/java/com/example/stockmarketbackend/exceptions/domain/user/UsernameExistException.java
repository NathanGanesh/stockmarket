package com.example.stockmarketbackend.exceptions.domain.user;

public class UsernameExistException extends Exception {
    public UsernameExistException(String message) {
        super(message);
    }
}
