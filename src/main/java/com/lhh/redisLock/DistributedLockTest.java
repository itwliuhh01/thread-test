package com.lhh.redisLock;

import java.util.concurrent.locks.Lock;

public class DistributedLockTest {

    private static Lock lock = new RedisDistributedLock();
    private static int customerCount = 100;
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

        Thread.currentThread().join();
    }

    public static class TicketRunnable implements Runnable{
        @Override
        public void run() {
            while(customerCount > 0){
//                lock.lock();
//                try {
//                    if(customerCount>0){
//                        System.out.println(Thread.currentThread().getName()+"售出第"+(customerCount--)+"张票");
//                     }
//                }catch (Exception e){
//                    e.printStackTrace();
//                }finally {
//                    lock.unlock();
//                }
                if(customerCount>0){
                    System.out.println(Thread.currentThread().getName()+"售出第"+(customerCount--)+"张票");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
