package com.example.demo.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController//标识该接口全部以json格式返回
public class HelloWorldController {

    @RequestMapping("/helloWorld")
    public String getRequestDemo (){
        return "success get param";
    }




}
