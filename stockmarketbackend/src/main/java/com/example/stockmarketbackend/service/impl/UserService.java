package com.example.stockmarketbackend.service.impl;

import com.example.stockmarketbackend.domain.User;
import com.example.stockmarketbackend.exception.domain.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User findUserByUsername(String s);


}

