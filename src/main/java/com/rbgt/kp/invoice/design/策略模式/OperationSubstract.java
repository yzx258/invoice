package com.rbgt.kp.invoice.design.策略模式;

/**
 * @author 俞春旺
 * @program: JueWei
 * @date 2021-04-02 22:31:08
 * @description: 描述
 */
public class OperationSubstract implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}
