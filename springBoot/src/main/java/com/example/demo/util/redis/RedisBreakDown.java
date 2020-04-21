package com.example.demo.util.redis;

import com.example.demo.service.UserService;
import jodd.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

/**
 *@Description: 缓存击穿
 *@Description: 缓存击穿表示恶意用户模拟请求很多缓存中不存在的数据，由于缓存中都没有，导致这些请求短时间内直接落在了数据库上，导致数据库异常。
 *@Description: 这个我们在实际项目就遇到了，有些抢购活动、秒杀活动的接口API被大量的恶意用户刷，导致短时间内数据库c超时了。
 *@Description: 好在数据库是读写分离，同时也有进行接口限流，hold住了。
 *@Author: zhangchao
 *@Date: 2020/4/21 12:35
*/
public class RedisBreakDown {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService redisService;
    /** 
     *@Description: 方案1、使用互斥锁排队  即根据key获取value值为空时，锁上，从数据库中load数据后再释放锁。
     *@Description: 若其它线程获取锁失败，则等待一段时间后重试。这里要注意，分布式环境中要使用分布式锁，单机的话用普通的锁（synchronized、Lock）就够了。
     *@Param: [key, jedis, lockKey, uniqueId, expireTime] 
     *@return: java.lang.String 
     *@Author: zhangchao
     *@Date: 2020/4/21 12:38
    */
    public String getWithLock(String key, Jedis jedis, String lockKey, String uniqueId, long expireTime) {
        // 通过key获取value
        String value = jedis.get(key);
        if (StringUtil.isEmpty(value)) {
            // 分布式锁，详细可以参考https://blog.csdn.net/fanrenxiang/article/details/79803037
            //封装的tryDistributedLock包括setnx和expire两个功能，在低版本的redis中不支持
            try {
                //boolean locked = redisService.tryDistributedLock(jedis, lockKey, uniqueId, expireTime);
                //if (locked) {
                if (true) {
                    //value = userService.getById(key);
                    jedis.set(key, value);
                    jedis.del(lockKey);
                    return value;
                } else {
                    // 其它线程进来了没获取到锁便等待50ms后重试
                    Thread.sleep(50);
                    getWithLock(key, jedis, lockKey, uniqueId, expireTime);
                }
            } catch (Exception e) {
                logger.error("getWithLock exception=" + e);
                return value;
            } finally {
              // redisService.releaseDistributedLock(jedis, lockKey, uniqueId);
            }
        }
        return value;

    }
}
