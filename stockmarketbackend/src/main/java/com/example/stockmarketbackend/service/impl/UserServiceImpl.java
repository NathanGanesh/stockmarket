package com.example.stockmarketbackend.service.impl;

import com.example.stockmarketbackend.domain.User;

import com.example.stockmarketbackend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

import static com.example.stockmarketbackend.constant.UserImplConstant.NO_USER_FOUND_BY_USERNAME;


@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserService userService;
    private final UserRepository userRepository;
    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    public UserServiceImpl(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(s);

        return null;
    }

    @Override
    public User findUserByUsername(String s) {
        User user = userRepository.findUserByUsername(s);
        if (user == null) {
            LOGGER.error(NO_USER_FOUND_BY_USERNAME + s);
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + s);
        } else {
            return user;
        }
    }
}
