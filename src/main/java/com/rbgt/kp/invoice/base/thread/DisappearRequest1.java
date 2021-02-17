package com.rbgt.kp.invoice.base.thread;

/**
 * @company： 厦门宜车时代信息技术有限公司
 * @copyright： Copyright (C), 2021
 * @author： yucw
 * @date： 2021/2/1 10:31
 * @version： 1.0
 * @description: 消失的请求【对象锁/类锁】
 * 核心思想
 * 1.一把锁只能同时被一个线程获取，没有拿到锁的线程必须等待
 * 2.每个实例都对应有自己一把锁，不同实例之间相互影响；例外：锁对象是*.class以及synchronized修饰的是static方法的时候，所有对象共用一把锁
 * 3.无论是方法正常执行完毕或者方法抛出异常，都会释放锁
 */
public class DisappearRequest1 {

    static int tickets = 10;

    class SellTickets implements Runnable {

        @Override
        public void run() {
            // 未加同步时产生脏数据
            while (tickets > 0) {
                synchronized (this){
                    System.out.println(Thread.currentThread().getName() + "--->售出第：  " + tickets + " 票");
                    tickets--;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            if (tickets <= 0) {
                System.out.println(Thread.currentThread().getName() + "--->售票结束！");
            }
        }
    }


    /**
     * 线程安全测试类
     * @param args
     */
    public static void main(String[] args) {
        SellTickets sell = new DisappearRequest1().new SellTickets();
        Thread thread1 = new Thread(sell, "1号窗口");
        Thread thread2 = new Thread(sell, "2号窗口");
        Thread thread3 = new Thread(sell, "3号窗口");
        Thread thread4 = new Thread(sell, "4号窗口");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();


    }


}
