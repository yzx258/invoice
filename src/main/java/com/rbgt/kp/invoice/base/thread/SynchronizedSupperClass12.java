package com.rbgt.kp.invoice.base.thread;

/**
 * @company： 厦门宜车时代信息技术有限公司
 * @copyright： Copyright (C), 2021
 * @author： yucw
 * @date： 2021/2/1 17:36
 * @version： 1.0
 * @description: 可重入粒度测试3：调用父类的方法
 */
public class SynchronizedSupperClass12 {
    public synchronized void doSomething1() {
        System.out.println("我是父类方法");
    }
}

class TestClass extends SynchronizedSupperClass12 {
    @Override
    public synchronized void doSomething1() {
        System.out.println("我是子类方法");
        super.doSomething1();
    }

    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        testClass.doSomething1();
    }
}
