package com.rbgt.kp.invoice.base.thread;

/**
 * @company： 厦门宜车时代信息技术有限公司
 * @copyright： Copyright (C), 2021
 * @author： yucw
 * @date： 2021/2/1 17:24
 * @version： 1.0
 * @description: 可重入粒度测试1：递归调用本方法
 */
public class SynchronizedRecursion10 {

    int a = 0;

    public static void main(String[] args) {
        SynchronizedRecursion10 synchronizedRecursion10 = new SynchronizedRecursion10();
        synchronizedRecursion10.method1();
    }

    private synchronized void method1(){
        System.out.println("这是method1,a = "+a);
        if(a == 0){
            a++;
            method1();
        }
    }

}
