package com.rbgt.kp.invoice.design.观察者模式;

/**
 * @author 俞春旺
 * @program: JueWei
 * @date 2021-04-02 22:43:25
 * @description: 描述
 */
public class MySubject extends AbstractSubject {

    @Override
    public void operation(String orderNo,String statusName) {
        System.out.println("update self!");
        notifyObservers(orderNo,statusName);
    }
}
