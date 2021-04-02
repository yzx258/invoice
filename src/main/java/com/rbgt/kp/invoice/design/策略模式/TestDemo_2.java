package com.rbgt.kp.invoice.design.策略模式;

/**
 * @author 俞春旺
 * @program: JueWei
 * @date 2021-04-02 22:34:05
 * @description: 描述
 */
public class TestDemo_2 {

    public static void main(String[] args) {
        //实例化对象的位置在调用处
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationSubstract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationMultiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }

}
