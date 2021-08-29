package com.example.stockmarketbackend.repository;

import com.example.stockmarketbackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByFirstNameAndLastName(String username, String lastName);

    User findUserByEmail(String email);

    User findUserByUserId(String userId);
}
