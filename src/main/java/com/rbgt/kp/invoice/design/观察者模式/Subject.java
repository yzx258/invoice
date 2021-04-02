package com.rbgt.kp.invoice.design.观察者模式;

/**
 * @author 俞春旺
 * @program: JueWei
 * @date 2021-04-02 22:40:18
 * @description: 描述
 */
public interface Subject {

    /**
     * 增加观察者
     */
    public void add(Observer observer);

    /**
     * 删除观察者
     * @param observer
     */
    public void del(Observer observer);

    /**
     * 通知所有的观察者
     * @param orderNo
     * @param statusName
     */
    public void notifyObservers(String orderNo,String statusName);

    /**
     * 自身的操作
     */
    public void operation(String orderNo,String statusName);

}
