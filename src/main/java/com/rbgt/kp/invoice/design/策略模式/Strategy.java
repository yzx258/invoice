package com.rbgt.kp.invoice.design.策略模式;

/**
 * @author 俞春旺
 * @program: JueWei
 * @date 2021-04-02 22:30:06
 * @description: 描述
 */
public interface Strategy {
    /**
     * 计算规则
     * @param num1
     * @param num2
     * @return
     */
    public int doOperation(int num1, int num2);
}
