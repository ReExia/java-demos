package com.example.testredis.testDemo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis pool链接
 */
public class test2 {

    public static void main(String[] args) {

        Jedis jedis = getJedis();

        long start = System.currentTimeMillis();
        try {
            for (int i= 0 ; i < 100 ; i++){
                jedis.set("test2"+i,String.valueOf(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }

        long end = System.currentTimeMillis();

        long costTime = end - start;
        System.out.println("use : " + costTime + "ms");
    }

    public static Jedis getJedis(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        //最大空闲数
        jedisPoolConfig.setMaxIdle(150);

        //最大等待时间
        jedisPoolConfig.setMaxTotal(100);

        //最大等待毫秒
        jedisPoolConfig.setMaxWaitMillis(20000);

        //使用配置创建连接池
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "localhost");

        //从连接池获取链接
        Jedis jedis = jedisPool.getResource();

        //验证密码
        jedis.auth("acdff1f516b14505af1960ff535ca3fb");

        return jedis;
    }

}
