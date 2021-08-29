package com.example.stockmarketbackend.service.user;

import com.example.stockmarketbackend.converter.UserRegisterDtoToUser;
import com.example.stockmarketbackend.domain.User;
import com.example.stockmarketbackend.domain.user.UserPrincipal;
import com.example.stockmarketbackend.dto.UserRegisterDto;
import com.example.stockmarketbackend.exceptions.domain.user.*;
import com.example.stockmarketbackend.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import java.util.Date;

import static com.example.stockmarketbackend.domain.enumeration.Role.ROLE_USER;
import static com.example.stockmarketbackend.domain.user.UserImplConstant.*;

@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRegisterDtoToUser userRegisterDtoToUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, UserRegisterDtoToUser userRegisterDtoToUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRegisterDtoToUser = userRegisterDtoToUser;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            LOGGER.error(NO_USER_FOUND_BY_USERNAME + email);
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + email);
        } else {
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);
            LOGGER.info(FOUND_USER_BY_USERNAME + email);
            return userPrincipal;
        }
    }



    @Override
    public User register(UserRegisterDto userRegisterDto) throws UserNotFoundException, UsernameExistException, EmailExistException, RegisterException, PasswordNotFoundException {
        User userByNewEmail = userRepository.findUserByEmail(StringUtils.deleteWhitespace(userRegisterDto.getEmail()));
        User userByNewName = userRepository.findUserByFirstNameAndLastName(StringUtils.deleteWhitespace(userRegisterDto.getFirstName()), StringUtils.deleteWhitespace(userRegisterDto.getLastName()));
        //try to find if there is already someone with the mail if there alreayd is someone throw an exception
        if (userByNewEmail != null) {
            throw new EmailExistException(EMAIL_ALREADY_EXISTS);
        }
        if (userByNewName != null) {
            if (StringUtils.isNotBlank(userRegisterDto.getPassword())){
                //so no user found with the username nor with email lets create the account and save it.
                userRegisterDto.setPassword(encodePassword(userRegisterDto.getPassword()));
                User convertedUser = userRegisterDtoToUser.convert(userRegisterDto);
                convertedUser.setActive(true);
                convertedUser.setNotLocked(true);
                convertedUser.setAuthorities(ROLE_USER.getAuthorities());
                convertedUser.setRole(ROLE_USER.name());
                convertedUser.setUserId(generateUserId());
                convertedUser.setJoinDate(new Date());
                userRepository.save(convertedUser);
                return convertedUser;
            }else{
                throw new PasswordNotFoundException("Password wasnt found");
            }
        } else {
            //if there is already someone with the username throw an exception
            throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
        }
    }



    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String generateUserId() {
        String s = RandomStringUtils.randomNumeric(10);
        boolean looping = true;
        User userByUserId = userRepository.findUserByUserId(s);
        while (looping){
            if (userByUserId!=null){
                s=RandomStringUtils.randomNumeric(10);
                userByUserId = userRepository.findUserByUserId(s);
            }else {
                looping = false;
            }
        }
        return s;
    }



}
