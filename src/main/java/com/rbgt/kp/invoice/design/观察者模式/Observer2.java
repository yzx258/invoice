package com.rbgt.kp.invoice.design.观察者模式;

/**
 * @author 俞春旺
 * @program: JueWei
 * @date 2021-04-02 22:39:49
 * @description: 描述
 */
public class Observer2 implements Observer {
    @Override
    public void update(String orderNo,String statusName) {
        System.out.println("observer2 has received!");
        System.out.println("订单编号：" + orderNo + ",订单状态："+statusName);
    }
}
