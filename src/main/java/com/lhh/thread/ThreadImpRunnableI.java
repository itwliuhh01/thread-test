package com.lhh.thread;

/**
 * 实现Runnable接口
 */
public class ThreadImpRunnableI implements Runnable{

    //定义变量
    private static int count=0;

    @Override
    public void run() {
        count++;
        System.out.println("当前线程的名称是：" + Thread.currentThread().getName()+"，count："+count);
    }

    public static void main(String[] args){
        ThreadImpRunnableI runnable1 = new ThreadImpRunnableI();

        Thread thread1 = new Thread(runnable1);
        thread1.setName("小姐姐1");

        Thread thread2 = new Thread(runnable1);
        thread2.setName("小姐姐2");

        Thread thread3 = new Thread(runnable1);
        thread3.setName("小姐姐3");

        //本质上还是调用了RunnableImplThread的run方法
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
