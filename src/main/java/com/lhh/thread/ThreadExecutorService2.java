package com.lhh.thread;

import java.util.List;
import java.util.concurrent.*;

public class ThreadExecutorService2 {
    
    public static void main(String[] args) throws InterruptedException {
        awaitTerminationTest();
    }

    public static void awaitTerminationTest() throws InterruptedException {
        //开启固定线程数的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i=0;i<5;i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "任务结束");
                }
            });
        }
        executorService.shutdown();
        //设置阻塞时间为2秒
        executorService.awaitTermination(10,TimeUnit.SECONDS);
        System.out.println("是否调用shutDown方法："+executorService.isShutdown()+"，任务是否全部结束terminated："+executorService.isTerminated());
    }

    public static void isTerminatedTest() {
        //开启固定线程大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0;i<5;i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "任务结束");
                }
            });
        }
        executorService.shutdown();
        while (true){
            if (executorService.isTerminated()){
                System.out.println("**********任务结束**************");
                break;
            }else{
                System.out.println("------任务没有结束-------");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void isShutDownTest(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("任务执行完成");
            }
        });
        System.out.println("是否调用isShutDown方法:"+executorService.isShutdown());
        executorService.shutdown();
        System.out.println("是否调用isShutDown方法:"+executorService.isShutdown());
    }
}
