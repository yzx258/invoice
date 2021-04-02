package com.rbgt.kp.invoice.design.抽象工厂模式;

import com.rbgt.kp.invoice.design.工厂模式.Sender;

/**
 * @author 俞春旺
 * @program: JueWei
 * @date 2021-04-02 22:28:14
 * @description: 描述
 */
public class TestDemo_1 {

    public static void main(String[] args) {
        Provider provider = new SendMailFactory();
        provider.produce().Send();
    }

}
