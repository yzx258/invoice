package com.rbgt.kp.invoice.base.thread;

/**
 * @company： 厦门宜车时代信息技术有限公司
 * @copyright： Copyright (C), 2021
 * @author： yucw
 * @date： 2021/2/1 15:19
 * @version： 1.0
 * @description: 方法抛异常后，会释放锁。展示不抛出异常和抛出异常后的对比：一旦抛出异常，第二个线程会立刻进入同步方法，意味着锁已经释放
 * 备注：JVM会主动的将 synchronized 释放锁
 */
public class SynchronizedException9 implements Runnable{
    /**
     * 创建对象锁
     */
    static SynchronizedException9 instance1 = new SynchronizedException9();

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
    public synchronized void method1(){
        System.out.println("我是静态加锁的方法1，我叫"+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
            throw new Exception();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
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
