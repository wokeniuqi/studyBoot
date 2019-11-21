package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserMybatisController {

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/findByName")
    @ResponseBody
    public User findByName (){
        User user= userMapper.findByName("likai");
        return user;
    }




}
