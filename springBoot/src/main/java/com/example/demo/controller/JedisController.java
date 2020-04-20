package com.example.demo.controller;

import com.example.demo.util.redis.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 *@Description: jedis实验控制器
 *@Author: zhangchao
 *@Date: 2020/4/12 20:52
*/
@RestController
public class JedisController {

    @RequestMapping("/testJedis")
    public void testJedis (){

        RedisUtil redisUtil = RedisUtil.getRedisUtil();
    }


    public static void main(String[] args) {
        //RedisUtil redisUtil = RedisUtil.getRedisUtil();
        //redisUtil.set("2323","4334");
        Jedis jedis = new Jedis("47.104.211.86",6379);
        jedis.auth("187459");
        String userName = jedis.get("username");
        System.out.println("用户名："+userName);
    }

}
