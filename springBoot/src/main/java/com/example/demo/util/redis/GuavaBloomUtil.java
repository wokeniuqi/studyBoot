package com.example.demo.util.redis;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 *@Description: 基于Guava实现的布隆过滤器
 *@Author: zhangchao
 *@Date: 2020/4/20 13:13
*/
public class GuavaBloomUtil {

    public static void main(String[] args) {
        // 1.创建符合条件的布隆过滤器
        // 预期数据量10000，错误率0.0001
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 10000, 0.0001);
        // 2.将一部分数据添加进去
        for (int i = 0; i < 5000; i++) {
            bloomFilter.put("" + i);
        }
        System.out.println("数据写入完毕");
        // 3.测试结果
        for (int i = 0; i < 10000; i++) {
            if (bloomFilter.mightContain("" + i)) {
                System.out.println(i + "存在");
            } else {
                System.out.println(i + "不存在");
            }
        }
    }
}
