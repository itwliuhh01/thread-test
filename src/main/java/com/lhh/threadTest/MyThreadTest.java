package com.lhh.threadTest;

import java.util.concurrent.*;

public class MyThreadTest {
    
    public static void main(String[] args){
        singleThread();
        multiThread();
    }

    /**模拟单线程，实现多个操作
       下电影10s,打农药 5s
     */
    public static void singleThread() {
        long start = System.currentTimeMillis();
        try {
            //下电影10s
            Thread.sleep(10*1000);
            //打农药 5s
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("单线程耗时"+(end-start)/1000+"秒");
    }
    /**模拟多线程，实现多个操作
     下电影10s,打农药 5s
     */
    public static void multiThread(){
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newCachedThreadPool();
        //下电影
        Future<String> future = executorService.submit(new Callable<String>(){
            @Override
            public String call() throws Exception {
                Thread.sleep(10*1000);
                return "爽";
            }
        });
        //下电影
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!future.isDone()){
            System.out.println("等待下电影结束");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //关闭线程
        executorService.shutdown();
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("多线程耗时"+(end-start)/1000+"秒");
    }

}
