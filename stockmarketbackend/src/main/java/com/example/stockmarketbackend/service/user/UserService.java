package com.example.stockmarketbackend.service.user;

import com.example.stockmarketbackend.domain.User;
import com.example.stockmarketbackend.dto.UserRegisterDto;
import com.example.stockmarketbackend.exceptions.domain.user.*;

import javax.mail.MessagingException;

public interface UserService {
    User register(UserRegisterDto userRegisterDto) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException, RegisterException, PasswordNotFoundException;

}
