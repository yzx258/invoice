package com.rbgt.kp.invoice.design.工厂模式;

/**
 * @author 俞春旺
 * @program: JueWei
 * @date 2021-04-02 22:18:17
 * @description: 描述
 */
public class TestDemo_0 {

    public static void main(String[] args) {
        // 发送短信
        SendFactory.produceSms().Send();
        // 发送Email
        SendFactory.produceMail().Send();
    }

}
