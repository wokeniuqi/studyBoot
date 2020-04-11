package com.example.demo.util.design.guanchazhe;

/**
 *@Description:  观察者的一个实现
 *@Author: zhangchao
 *@Date: 2020/1/17 16:20
*/
public class ConcreteObserver implements Observer {
    public void update() {
        System.out.println("接收到信息，并进行处理！");
    }
}
