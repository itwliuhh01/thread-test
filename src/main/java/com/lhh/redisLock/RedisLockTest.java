package com.lhh.redisLock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisLockTest {

    public static void main(String[] args){
        JedisPool jedisPool = RedisPoolFactory.getJedisPool();
        Jedis jedis = jedisPool.getResource();
        jedis.set("name","haihua");
        jedis.close();
    }
}
