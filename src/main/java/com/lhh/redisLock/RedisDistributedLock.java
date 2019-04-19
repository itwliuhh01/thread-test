package com.lhh.redisLock;

import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

//redis实现分布式的锁
public class RedisDistributedLock implements Lock {

    //用来存储随机产生的id，解锁的时候需要根据id来释放锁
    private ThreadLocal<String> lockContext = new ThreadLocal<String>();
    private long time = 100;
    private Thread excThread;
    static String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";

    @Override
    public void lock() {
        //尝试加锁
        if(tryLock()){
            return;
        }
        //加锁失败
        try {
            //加锁失败，睡眠一会重新加锁
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //递归加锁再次尝试加锁
        lock();
    }

    @Override
    public boolean tryLock() {
        String id = UUID.randomUUID().toString();
        Jedis jedis = RedisPoolFactory.getJedisPool().getResource();
//        String result = jedis.set("redis",id,"NX","PX",time);
        if(jedis.setnx("redis",id) == 1){
            jedis.pexpire("redis",time);
            lockContext.set(id);
            return true;
        }
        //加锁失败
        return false;
    }

    /**
     * 解锁
     */
    @Override
    public void unlock() {
        Jedis jedis = RedisPoolFactory.getJedisPool().getResource();
        jedis.eval(luaScript, Arrays.asList("redis"),Arrays.asList(lockContext.get()));
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }
    @Override
    public Condition newCondition() {
        return null;
    }
    @Override
    public void lockInterruptibly() throws InterruptedException { }
}
