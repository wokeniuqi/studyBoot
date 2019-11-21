package com.example.demo.service.impl;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createUser(String username, String name) {
        System.out.println("ssss");
        jdbcTemplate.update("insert into user_test values(?,?,?);", 1,username, name);
    }

}
