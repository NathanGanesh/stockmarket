package com.example.stockmarketbackend.exceptions.domain.user;

public class EmailExistException extends Exception {
    public EmailExistException(String message) {
        super(message);
    }
}
