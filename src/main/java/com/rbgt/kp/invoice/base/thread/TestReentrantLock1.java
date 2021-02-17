package com.rbgt.kp.invoice.base.thread;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @company： 厦门宜车时代信息技术有限公司
 * @copyright： Copyright (C), 2021
 * @author： yucw
 * @date： 2021/2/4 17:47
 * @version： 1.0
 * @description: lock声明为局部变量，使用lock
 */
public class TestReentrantLock1 {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();

    public static void main(String[] args) {
        final TestReentrantLock1 test = new TestReentrantLock1();

        new Thread() {
            @Override
            public void run() {
                test.insert(Thread.currentThread());
            }
            ;
        }.start();

        new Thread() {
            @Override
            public void run() {
                test.insert(Thread.currentThread());
            }
            ;
        }.start();
    }
    public void insert(Thread thread) {
        // 注意这个地方
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println(thread.getName() + "得到了锁");
            Thread.sleep(3000);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("获取锁出错了");
        } finally {
            System.out.println(thread.getName() + "释放了锁");
            lock.unlock();
        }
    }
}
