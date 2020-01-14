package com.example.demo.util.design.guanchazhe;

import java.util.Vector;

/**
 *@Description: 被观察者
 *@Author: zc
 *@Date: 2020/1/13 18:51
*/
public abstract class Subject {
    //定义一个观察者数组
    private Vector<Observer> obsVector = new Vector<Observer>();
    //增加一个观察者
    public void addObserver(Observer o){
        this.obsVector.add(o);
    }
    //删除一个观察者
    public void delObserver(Observer o){
        this.obsVector.remove(o);
    }
    //通知所有观察者
    //这里可以考虑用parallelStream
    public void notifyObservers(){
        for(Observer o:this.obsVector){
            o.update();
        }
    }
}