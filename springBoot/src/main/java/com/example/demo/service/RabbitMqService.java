package com.example.demo.service;


import java.util.Map;


public interface RabbitMqService {


    void sendBaseMessage(String key, Map<String, Object> contentMap);
}
