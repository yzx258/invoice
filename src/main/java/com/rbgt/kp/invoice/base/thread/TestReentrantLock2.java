package com.rbgt.kp.invoice.base.thread;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @company： 厦门宜车时代信息技术有限公司
 * @copyright： Copyright (C), 2021
 * @author： yucw
 * @date： 2021/2/4 17:50
 * @version： 1.0
 * @description: lock声明为类的属性，使用lock
 */
public class TestReentrantLock2 {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    // 注意这个地方
    private Lock lock = new ReentrantLock();

    public static void main(String[] args)  {
        final TestReentrantLock2 test = new TestReentrantLock2();

        new Thread(){
            @Override
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();

        new Thread(){
            @Override
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();
    }

    public void insert(Thread thread) {
        lock.lock();
        try {
            System.out.println(thread.getName()+"得到了锁");
            Thread.sleep(3000);
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            System.out.println(thread.getName()+"释放了锁");
            lock.unlock();
        }
    }
}
