package com.example.demo.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

//切面编程
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    //如果返回json格式 @ResponseBody 返回页面 返回string类型 类型结果指定404页面
    @ResponseBody
    public Map<String, Object> resultError() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errorCode", "500");
        result.put("errorMsg", "系统错误!");
        return result;
    }
}
