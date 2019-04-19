package com.lhh.threadTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {
    
    public static void main(String[] args) throws InterruptedException {
        isTerminated();
    }

    public static void awaitTermination(){
        //初始化固定线程个数的线程池
        ExecutorService poll = Executors.newFixedThreadPool(3);
        for (int i=0;i<10;i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"执行完成");
                }
            };
            poll.submit(runnable);
        }
        //关闭线程池，不再接受任务
        poll.shutdown();
        try {
            poll.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("查看所有的任务是否完成"+poll.isTerminated());

    }


    public static void isTerminated(){
        //初始化固定线程个数的线程池
        ExecutorService poll = Executors.newFixedThreadPool(3);
        for (int i=0;i<10;i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"执行完成");
                }
            };
            poll.submit(runnable);
        }
        //关闭线程池，不再接受任务
        poll.shutdown();
        while (true){
            if(poll.isTerminated()){
                System.out.println("所有任务结束");
                System.out.println("//继续做下面的事情****");
                break;
            }
        }
    }

    public static void await() throws InterruptedException {
        int number = 5;
        CountDownLatch countDownLatch = new CountDownLatch(number);
        ExecutorService poll = Executors.newFixedThreadPool(number);
        for (int i=0;i<number;i++) {
            poll.submit(new Runnable() {
                @Override
                public void run() {
                    countDownLatch.countDown();
                    System.out.println("任务执行完毕");
                }
            });
        }
        //使当前线程等待
        countDownLatch.await();
        poll.shutdown();
    }
}
