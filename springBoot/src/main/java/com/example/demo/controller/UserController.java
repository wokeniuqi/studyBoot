package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.util.redissoon.DistributedRedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController//标识该接口全部以json格式返回
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/addUser")
    public String addUser (){

        //加锁
        String key = "test123";
        DistributedRedisLock.acquire(key);
         userService.createUser("likai","wang");
        //释放锁
        DistributedRedisLock.release(key);
        return "index";
    }


    @RequestMapping("/getUser")
    public String getUser (Integer id){

        return   userService.findById(id).toString();
    }



}
