package com.orm;

import com.demo.orm.core.pools.DatasourcePoolManager;

import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * 测试连接池单例
 */
public class TestDatasourcePoolManager {

    public static void main(String[] args) {

        /*System.out.println(DatasourcePoolManager.getInstance().toString());
        System.out.println(DatasourcePoolManager.getInstance().toString());
        System.out.println(DatasourcePoolManager.getInstance().toString());*/

        int count = 50;

        CountDownLatch countDownLatch = new CountDownLatch(count);

        Set<Object> set = Collections.synchronizedSet(new HashSet<>());
        List<Object> list = new ArrayList<>();

        for (int i = 0 ; i < count ; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    set.add(DatasourcePoolManager.getInstance());
                    list.add(DatasourcePoolManager.getInstance());
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

        System.out.println(String.format("set size is %s", set.size()));
        System.out.println(String.format("list size is %s", list.size()));

    }

}
