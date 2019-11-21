package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller//标识该接口全部以json格式返回
public class IndexController {

    @RequestMapping("/index")
    public String getRequestDemo (Map<String, Object> map){
        map.put("name","美丽的天使...");
        return "index";
    }




}
