package com.example.demo.service.impl;

import com.example.demo.service.RabbitMqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service

public class RabbitMqServiceImpl implements RabbitMqService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${rabbit.common.exchange:s1-base-bus}")
    public String S1_BASE_EXCHANGE;


    /**
     * mq消息模板
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    @Async
    public void sendBaseMessage(String key,  Map<String, Object> contentMap) {
        String body = jsonConverter.toJson(contentMap);
        logger.info("================进入消息发送================");
        logger.info("消息Key：【{}】", key);
        logger.info("消息内容：【{}】", jsonConverter.toJson(contentMap));
        rabbitTemplate.convertSendAndReceive(S1_BASE_EXCHANGE, key, body);
        logger.info("================消息发送完成================");
    }

}
