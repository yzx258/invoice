package com.rbgt.kp.invoice.design.抽象工厂模式;

import com.rbgt.kp.invoice.design.工厂模式.Sender;
import com.rbgt.kp.invoice.design.工厂模式.SmsSender;

/**
 * @author 俞春旺
 * @program: JueWei
 * @date 2021-04-02 22:26:30
 * @description: 描述
 */
public class SendSmsFactory implements Provider{
    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
