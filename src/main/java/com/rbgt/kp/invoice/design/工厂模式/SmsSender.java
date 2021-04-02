package com.rbgt.kp.invoice.design.工厂模式;

/**
 * @author 俞春旺
 * @program: JueWei
 * @date 2021-04-02 22:17:17
 * @description: 描述
 */
public class SmsSender implements Sender{
    @Override
    public void Send() {
        System.out.println("this is sms sender!");
    }
}
