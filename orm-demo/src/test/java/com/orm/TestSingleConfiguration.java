package com.orm;


import com.demo.orm.core.config.PropertiesConfiguration;

import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * 测试单例
 */
public class TestSingleConfiguration {

    public static void main(String[] args) {

        /*System.out.println(PropertiesConfiguration.getInstance().toString());
        System.out.println(PropertiesConfiguration.getInstance().toString());
        System.out.println(PropertiesConfiguration.getInstance().toString());*/

        Set<Object> set = Collections.synchronizedSet(new HashSet<>());
        List<Object> list = new ArrayList<>();

        //改为50000是为了看出性能问题
        int count = 50000;
        //int count = 50;

        CountDownLatch countDownLatch = new CountDownLatch(count);

        for (int i = 0; i < count; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    set.add(PropertiesConfiguration.getInstance());
                    list.add(PropertiesConfiguration.getInstance());
                    countDownLatch.countDown();
                }
            }).start();
        }

        //等待所有任务执行完
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(String.format("set size is %s ", set.size()));
        System.out.println(String.format("list size is %s", list.size()));
    }

}
