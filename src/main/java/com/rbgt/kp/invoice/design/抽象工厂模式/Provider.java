package com.rbgt.kp.invoice.design.抽象工厂模式;

import com.rbgt.kp.invoice.design.工厂模式.Sender;

/**
 * @author 俞春旺
 * @program: JueWei
 * @date 2021-04-02 22:20:35
 * @description: 描述：工厂方法模式有一个问题就是，类的创建依赖工厂类，也就是说，如果想要拓展程序，必须对工厂类进行修改，这违背了闭包原则
 */
public interface Provider {

    /**
     * 发送信息业务
     */
    public Sender produce();

}
