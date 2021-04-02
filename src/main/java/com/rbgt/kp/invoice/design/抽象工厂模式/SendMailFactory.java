package com.rbgt.kp.invoice.design.抽象工厂模式;

import com.rbgt.kp.invoice.design.工厂模式.MailSender;
import com.rbgt.kp.invoice.design.工厂模式.Sender;

/**
 * @author 俞春旺
 * @program: JueWei
 * @date 2021-04-02 22:25:10
 * @description: 描述
 */
public class SendMailFactory implements Provider{
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
