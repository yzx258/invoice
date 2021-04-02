package com.rbgt.kp.invoice.design.观察者模式;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author 俞春旺
 * @program: JueWei
 * @date 2021-04-02 22:41:48
 * @description: 描述
 */
public abstract class AbstractSubject implements Subject {

    private Vector<Observer> vector = new Vector<Observer>();
    @Override
    public void add(Observer observer) {
        vector.add(observer);
    }

    @Override
    public void del(Observer observer) {
        vector.remove(observer);
    }

    @Override
    public void notifyObservers(String orderNo,String statusName) {
        Enumeration<Observer> enumo = vector.elements();
        while(enumo.hasMoreElements()){
            enumo.nextElement().update(orderNo,statusName);
        }
    }
}
