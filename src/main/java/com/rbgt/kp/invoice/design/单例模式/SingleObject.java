package com.rbgt.kp.invoice.design.单例模式;

/**
 * @author 俞春旺
 * @program: JueWei
 * @date 2021-04-02 22:35:13
 * @description: 描述：只有一个对象被创建
 */
public class SingleObject {

    // 创建 SingleObject 的一个对象
    private static SingleObject instance = new SingleObject();

    // 让构造函数为 private，这样该类就不会被实例化
    private SingleObject(){}

    // 获取唯一可用的对象
    public static SingleObject getInstance(){
        return instance;
    }

}
