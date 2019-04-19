package com.lhh.thread;

/**
 * 继承Thread
 */
public class Threadimpl extends Thread{

    //定义变量
    private static int count=0;

    //自定义线程的名称
    public Threadimpl(String name){
        super(name);
    }
    @Override
    public void run() {
        count++;
        System.out.println("当前线程的名称是："+Thread.currentThread().getName()+"，count："+count);
    }
    public static void main(String[] args){
        Thread thread1 = new Threadimpl("小姐姐1");
        thread1.start();
        Thread thread2 = new Threadimpl("小姐姐2");
        thread2.start();
        Thread thread3 = new Threadimpl("小姐姐3");
        thread3.start();
    }

}
