package com.example.demo.util.design.guanchazhe;

public class ConcreteSubject extends Subject {
    //具体的业务
    public void doSomething()  {
        super.notifyObservers();
    }

}
