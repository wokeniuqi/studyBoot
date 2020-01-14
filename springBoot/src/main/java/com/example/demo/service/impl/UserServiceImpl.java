package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDao userDao;

    public void createUser(String username, String name) {
        System.out.println("ssss");
        jdbcTemplate.update("insert into user_test values(?,?,?);", 1,username, name);
    }


    /**
     *@Description: 根据id查询实体
     *@Param: [
     *@return: void
     *@Author: zhangchao
     *@Date: 2019/12/14 15:05
    */
    public User findById(Integer id) {
        List<User> list =userDao.findAll();
        User u= userDao.findAll().get(0);
        return u;
    }

}
