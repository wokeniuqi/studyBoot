package com.example.demo.controller;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.ServiceDemoService;

import javax.annotation.Resource;

@RestController
public class ResultfulController {

    @Resource
    private ServiceDemoService serviceDemoService;

    /**
     * 通过设置RequestMapping的method属性便可以设置该方法可处理的对应请求了，例如下面的getRequestDemo方法只会处理get请求
     */
    @RequestMapping(path = {"/getRequestDemo"},method = RequestMethod.GET)
    public String getRequestDemo (@RequestParam(value="param",required = false) int param){
        System.out.println("例子名称"+serviceDemoService.queryDemo());
        System.out.println("get request test ,get param " + param);
        return "success get param";
    }

    /**
     * 下面的deleteRequestDemo方法只会处理post请求
     */
    @RequestMapping(path = {"/deleteRequestDemo"},method = RequestMethod.POST)
    public String deleteRequestDemo (@RequestParam(value="param",required = false) int param){

        System.out.println("delete request test ,get param " + param);

        System.out.println("例子名称"+serviceDemoService.queryDemo());

        return "success get param";
    }


}
