package com.example.testredis.testDemo;

import redis.clients.jedis.Jedis;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;

/**
 * 手动链接redis
 */
public class Test1 {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);

        jedis.auth("acdff1f516b14505af1960ff535ca3fb");
        long start = System.currentTimeMillis();
        try {
            for (int i= 0 ; i < 100 ; i++){
                jedis.set("test"+i,String.valueOf(i));
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

}
