package com.rbgt.kp.invoice.design.工厂模式;

/**
 * @author 俞春旺
 * @program: JueWei
 * @date 2021-04-02 22:17:43
 * @description: 描述：创建工厂
 */
public class SendFactory {

    public static Sender produceMail(){
        return new MailSender();
    }

    public static Sender produceSms(){
        return new SmsSender();
    }

}
