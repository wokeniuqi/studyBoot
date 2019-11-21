package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController//标识该接口全部以json格式返回
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/addUser")
    public String addUser (){
         userService.createUser("likai","wang");
        return "index";
    }




}
