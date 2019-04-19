package com.lhh.lock;

import java.util.concurrent.CountDownLatch;

public class Movie2{

    //剩余的英雄联盟票数
    private static Integer heroTicketCount = 8;
    //失恋三十三天剩余票数
    private static int shiLianTicketCont = 5;

    //同时购买票的客户数量
    private static int customer=10;

    public static void main(String[] args){
        Movie2 movie2 = new Movie2();
        movie2.buyHeroTicket();
//        movie2.buyShiLianTicket();
    }

    public void buyHeroTicket() {
        CountDownLatch countDownLatch = new CountDownLatch(customer);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //线程阻塞
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (heroTicketCount) {
                    if (heroTicketCount > 0) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("线程" + Thread.currentThread().getName() + "正在购英雄联盟票，还剩余" + (heroTicketCount--) + "张票");
                    }else{
                        System.out.println("线程" + Thread.currentThread().getName() + "正在购英雄联盟票，还剩余" + (heroTicketCount--) + "张票,购票失败");
                    }
                }
            }
        };
        for (int i = 0; i < customer; i++) {
            new Thread(runnable).start();
            //线程等待计数器减1
            countDownLatch.countDown();
        }
    }
    public synchronized void buyShiLianTicket(){
        CountDownLatch countDownLatch = new CountDownLatch(customer);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    countDownLatch.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                synchronized (this) {
                    if (shiLianTicketCont > 0) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("线程" + Thread.currentThread().getName() + "正在购失恋三十三天票，还剩余" + (shiLianTicketCont--) + "张票");
                    }
                }
//            }
        };
        for (int i=0;i<customer;i++){
            new Thread(runnable).start();
            //线程等待计数器减1
            countDownLatch.countDown();
        }
    }
}