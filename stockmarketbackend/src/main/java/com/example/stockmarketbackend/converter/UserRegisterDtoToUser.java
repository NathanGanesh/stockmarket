package com.example.stockmarketbackend.converter;

import com.example.stockmarketbackend.domain.User;
import com.example.stockmarketbackend.dto.UserRegisterDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterDtoToUser implements Converter<UserRegisterDto, User> {


    @Override
    public User convert(UserRegisterDto userRegisterDto) {
        User user = new User();

        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(userRegisterDto.getPassword());

        return user;
    }

}
