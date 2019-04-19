package com.lhh.redisLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    //redis分布式锁
    private static Lock lock = new RedisDistributedLock();
    private static int customerCount = 10;
    public static void main(String[] args) throws InterruptedException {
        TicketRunnable ticketRunnable = new TicketRunnable();
        Thread t1 = new Thread(ticketRunnable,"窗口A");
        Thread t2 = new Thread(ticketRunnable,"窗口B");
        Thread t3 = new Thread(ticketRunnable,"窗口C");
        Thread t4 = new Thread(ticketRunnable,"窗口D");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        //等上面的线程执行完，再执行下面的语句
        Thread.currentThread().join();
        System.out.println("所有的线程已经执行完毕。。。。");
    }

    public static class TicketRunnable implements Runnable{
        @Override
        public void run() {
            while(customerCount > 0){
                try {
                    lock.lock();
                    if(customerCount>0){
                        System.out.println(Thread.currentThread().getName()+"售出第"+(customerCount--)+"张票");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    //解锁放在finally是为了防止死锁
                    //当在解锁之后，业务里面发生了异常，需要解锁，否则会死锁
                    lock.unlock();
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
