package com.rbgt.kp.invoice.design.观察者模式;

/**
 * @author 俞春旺
 * @program: JueWei
 * @date 2021-04-02 22:38:23
 * @description: 描述：一个对象(subject)被其他多个对象(observer)所依赖。则当一个对象变化时，发出通知，其它依赖该对象的对象都会收到通知，并且随着变化
 */
public interface Observer {
    /**
     * 通知变更的数据
     */
    public void update(String orderNo,String statusName);
}
