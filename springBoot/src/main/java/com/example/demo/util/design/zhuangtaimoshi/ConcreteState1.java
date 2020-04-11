package com.example.demo.util.design.zhuangtaimoshi;

/**
 *@Description:
 *@Author: zhangchao
 *@Date: 2020/1/17 16:33
*/
public class ConcreteState1 extends State {

    @Override
    public void handle1() {
    //本状态下必须处理的逻辑
        System.out.println("状态类型为1");
    }
    @Override
    public void handle2() {
    //设置当前状态为stat2
        super.context.setCurrentState(Context.STATE2);
    //过渡到state2状态，由Context实现
        super.context.handle2();
    }

}