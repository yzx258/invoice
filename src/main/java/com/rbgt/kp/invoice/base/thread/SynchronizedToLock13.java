package com.rbgt.kp.invoice.base.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @company： 厦门宜车时代信息技术有限公司
 * @copyright： Copyright (C), 2021
 * @author： yucw
 * @date： 2021/2/1 17:53
 * @version： 1.0
 * @description: 枷锁和释放锁的原理：method1 = method2
 */
public class SynchronizedToLock13 {

    Lock lock = new ReentrantLock();

    /**
     * 隐藏加锁
     */
    public synchronized void method1(){
        System.out.println("我是synchronized形式锁");
    }

    /**
     * 加锁原理
     */
    public void method2(){
        lock.lock();
        try {
            System.out.println("我是lock形式的锁");
        }finally {
            // 释放锁
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SynchronizedToLock13 synchronizedToLock13 = new SynchronizedToLock13();
        synchronizedToLock13.method1();
        synchronizedToLock13.method2();
    }

}
