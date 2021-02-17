package com.rbgt.kp.invoice.base.thread;

/**
 * @company： 厦门宜车时代信息技术有限公司
 * @copyright： Copyright (C), 2021
 * @author： yucw
 * @date： 2021/2/1 17:33
 * @version： 1.0
 * @description: 可重入粒度测试1：调用类内部的另外方法
 */
public class SynchronizedOtherMethod11 {

    public synchronized void method1(){
        System.out.println("我是method1");
        method2();
    }

    public synchronized void method2(){
        System.out.println("我是method2");
    }

    public static void main(String[] args) {
        SynchronizedOtherMethod11 synchronizedOtherMethod11 = new SynchronizedOtherMethod11();
        synchronizedOtherMethod11.method1();
    }
}
