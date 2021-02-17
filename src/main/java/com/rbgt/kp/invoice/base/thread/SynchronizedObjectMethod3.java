package com.rbgt.kp.invoice.base.thread;

/**
 * @company： 厦门宜车时代信息技术有限公司
 * @copyright： Copyright (C), 2021
 * @author： yucw
 * @date： 2021/2/1 14:48
 * @version： 1.0
 * @description: 对象锁实例2，方法锁形式
 */
public class SynchronizedObjectMethod3 implements Runnable{

    /**
     * 创建对象锁
     */
    static SynchronizedObjectMethod3 instance1 = new SynchronizedObjectMethod3();

    @Override
    public void run() {
        method();
    }

    public synchronized void method(){
        System.out.println("我是对象锁的方法形式，我叫"+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"运行结束");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance1);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("线程执行完毕");
    }
}
