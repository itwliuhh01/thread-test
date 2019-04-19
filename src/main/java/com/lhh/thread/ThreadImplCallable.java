package com.lhh.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

/**
 * 实现Callable的接口
 */
public class ThreadImplCallable implements Callable {
    @Override
    public String call() throws Exception {
        System.out.println("当前线程的名称是："+Thread.currentThread().getName());
        return "success";
    }
    public static void main(String[] args){

        ThreadImplCallable threadImplCallable = new ThreadImplCallable();
        //由callable创建一个FutureTask
        FutureTask futureTask = new FutureTask(threadImplCallable);
        Thread thread = new Thread(futureTask);
        thread.start();

    }
}
