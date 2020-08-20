package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.service.ServiceDemoService;

@Service
public class ServiceDemoServiceImpl implements ServiceDemoService{
    @Override
    public String queryDemo(){
        return "11";
    }
}
