package com.example.demo.util;

import java.util.concurrent.CountDownLatch;

public class MaxThreadsDemo {
    /** 
     *@Description: 线程池创建多并不合适 
     *@Param: [args] 
     *@return: void 
     *@Author: zhangchao
     *@Date: 2019/11/19 21:37
    */
    public static void main(String[] args) {
        CountDownLatch cd1 = new CountDownLatch(1);
        for(int i=0;i<5000;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cd1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            System.out.println("i="+i);
        }
    }
}
