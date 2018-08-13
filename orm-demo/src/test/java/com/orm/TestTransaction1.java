package com.orm;

import com.demo.orm.core.JdbcUtils;
import com.demo.orm.core.template.HandlerTemplate;
import com.demo.orm.core.template.mysql.MysqlTemplate;
import com.demo.orm.example.entity.Account;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试事务
 */
public class TestTransaction1 {

    private static HandlerTemplate mysqlTemplate = new MysqlTemplate();

    static ReentrantLock lock = new ReentrantLock();

    private static void zhuangzhang(int sourceId, int targetId, int money) {
        JdbcUtils session = new JdbcUtils();
        try {
            lock.lock();

            List<Account> accounts = mysqlTemplate.queryAll(new Account());

            Account a = new Account();
            Account b = new Account();

            for (Account account : accounts) {
                if (account.getUsername().equals("张三")){
                    a = account;
                }else {
                    b = account;
                }
            }


            // 开启事务
            session.begin();
            a.setMoney(a.getMoney() - money);

            //测试回滚
            //int number = 1/0;

            b.setMoney(b.getMoney() + money);

            // 更新a和b用户的账户
            mysqlTemplate.update(a);
            // int b1 = 4/0; // 出现异常测试是正常回滚
            mysqlTemplate.update(b);
            // 事务提交
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 事务的回滚
            session.rollback();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        //zhuangzhang(1,2,100);

        int count = 1500;
        // 定义发令枪
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    zhuangzhang(1, 2, 1);
                    latch.countDown(); // 每次自减1,当为0的时候放开阻塞
                }
            }).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
