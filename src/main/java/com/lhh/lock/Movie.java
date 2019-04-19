package com.lhh.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Movie implements Runnable{

    //剩余的票数
    private static int ticketCount=8;
    //同时购买票的客户数量
    private static int customer=10;
    private CountDownLatch countDownLatch = new CountDownLatch(customer);

    public static void main(String[] args){
        Movie movie = new Movie();
//        ExecutorService fixSizePoll = Executors.newFixedThreadPool(3);
        for (int i=0;i<customer;i++){
            //线程等待计数器减1
            movie.countDownLatch.countDown();
            new Thread(movie).start();
//            fixSizePoll.submit(movie);
        }
        //关闭线程池，不再接受任务
//        fixSizePoll.shutdown();
    }

    @Override
    public void run() {
        try {
            //线程阻塞
            countDownLatch.await();
            synchronized (this) {
                if (ticketCount > 0) {
                    Thread.sleep(10);
                    System.out.println("线程" + Thread.currentThread().getName() + "正在购票，还剩余" + (ticketCount--) + "张票");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
