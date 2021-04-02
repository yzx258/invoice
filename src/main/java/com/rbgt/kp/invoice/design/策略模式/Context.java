package com.rbgt.kp.invoice.design.策略模式;

/**
 * @author 俞春旺
 * @program: JueWei
 * @date 2021-04-02 22:32:23
 * @description: 描述
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }

}
