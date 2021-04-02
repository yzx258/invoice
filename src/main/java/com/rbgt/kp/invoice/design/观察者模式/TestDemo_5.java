package com.rbgt.kp.invoice.design.观察者模式;

/**
 * @author 俞春旺
 * @program: JueWei
 * @date 2021-04-02 22:46:02
 * @description: 描述
 */
public class TestDemo_5 {

    public static void main(String[] args) {
        Subject sub = new MySubject();
        //订阅这个对象
        sub.add(new Observer1());
        sub.add(new Observer2());
        //发出改变的一个通知
        sub.operation("C1000562","完成交车");
    }

}
