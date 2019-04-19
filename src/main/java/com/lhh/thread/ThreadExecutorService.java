package com.lhh.thread;

import java.util.concurrent.*;

public class ThreadExecutorService {
    
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

//        //传入Runnable对象
//        for (int i=0;i<5;i++) {
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread().getName()+"上班了");
//                }
//            });
//        }
//        for (int i=0;i<5;i++) {
           Future future = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(6*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },String.class);
            while (!future.isDone()){
                System.out.println("线程没有执行完成");
                Thread.sleep(1000);
            }
            System.out.println("。。。。。。。线程执行完成。。。。。。。");
//            executorService.shutdown();
//        }
//        //传入Callable对象
        for (int i=0;i<5;i++) {
            Future<String> result = executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println(Thread.currentThread().getName()+"上班了");
                    return "sucess";
                }
            });

        }

//        CountDownLatch countDownLatch = new CountDownLatch(200);

    }
}
