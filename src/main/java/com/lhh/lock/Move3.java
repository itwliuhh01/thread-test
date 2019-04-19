package com.lhh.lock;

import java.util.concurrent.CountDownLatch;

public class Move3 {
    //剩余的英雄联盟票数
    private static Integer heroTicketCount = 8;
    //失恋三十三天剩余票数
    private static Integer shiLianTicketCont = 5;
    //同时购买票的客户数量
    private static int customer=10;
    private final   CountDownLatch countDownLatch = new CountDownLatch(10);
    
    public static void main(String[] args){
        Move3 move3 = new Move3();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    move3.showHeroTicket();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    move3.showShiLianTicket();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    //查询剩余票数方法
    public void showHeroTicket() throws InterruptedException {
        synchronized (heroTicketCount) {
            System.out.println("英雄联盟剩余票数为：" + heroTicketCount);
            //模拟方法执行耗时较长
            countDownLatch.await();
        }
    }

    //查询剩余票数方法
    public void showShiLianTicket() throws InterruptedException {
        synchronized (shiLianTicketCont) {
            System.out.println("失恋三十三天剩余票数为：" + shiLianTicketCont);
            //模拟方法执行耗时较长
            countDownLatch.await();
        }
    }


}
