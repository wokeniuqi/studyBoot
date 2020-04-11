package com.example.demo.util.design.zhuangtaimoshi;

/**
 *@Description: 具体环境角色
 *@Author: zc
 *@Date: 2020/1/17 16:38
*/
public class Client {
    public static void main(String[] args) {
        //定义环境角色
        Context context = new Context();
        //初始化状态
        context.setCurrentState(new ConcreteState1());
        //行为执行
        context.handle1();
        context.handle2();
    }
}
