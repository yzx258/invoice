package com.rbgt.kp.invoice.base.thread;

/**
 * @company： 厦门宜车时代信息技术有限公司
 * @copyright： Copyright (C), 2021
 * @author： yucw
 * @date： 2021/2/1 15:19
 * @version： 1.0
 * @description: 同时访问synchronized静态与非synchronized静态方法
 */
public class SynchronizedStaticAndNormal8 implements Runnable{
    /**
     * 创建对象锁
     */
    static SynchronizedStaticAndNormal8 instance1 = new SynchronizedStaticAndNormal8();

    @Override
    public void run() {
        if(Thread.currentThread().getName().equals("Thread-0")){
            method1();
        }else{
            method2();
        }
    }

    /**
     * synchronized 拿到的是*.class的锁
     */
    public static synchronized void method1(){
        System.out.println("我是静态加锁的方法1，我叫"+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"运行结束");
    }

    /**
     * synchronized 拿到的是this锁
     */
    public synchronized void method2(){
        System.out.println("我是非静态枷锁，我叫"+Thread.currentThread().getName());
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
