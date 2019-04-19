package com.lhh.threadTest;

//模拟顾客数量为负数的不安全问题
public class CustomerThread extends Thread{

    //定义10个顾客
    private static int customers=10;

    public CustomerThread(){}
    //构造方法
    public CustomerThread(String name){
        super(name);
    }

    @Override
    public void run() {
        super.run();
        synchronized (CustomerThread.class) {
            while (true) {
                if (customers > 0) {
                    try {
                        //睡眠100毫秒
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "开始服务，剩余顾客数量：" + customers--);
                } else {
                    break;
                }
            }
        }
    }
    
    public static void main(String[] args){

        CustomerThread customer1 = new CustomerThread("小姐姐1");
        CustomerThread customer2 = new CustomerThread("小姐姐2");
        CustomerThread customer3 = new CustomerThread("小姐姐3");

        customer1.start();
        customer2.start();
        customer3.start();
    }
}
