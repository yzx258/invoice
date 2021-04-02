package com.rbgt.kp.invoice.design.策略模式;

/**
 * @author 俞春旺
 * @program: JueWei
 * @date 2021-04-02 22:31:38
 * @description: 描述：一个系统需要动态地在几种类似的算法中选择一种
 */
public class OperationMultiply implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}
