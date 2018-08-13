package com.demo.orm.core.transaction;

import com.demo.orm.core.exception.MyOrmException;

/**
 *  @author setsuna
 * 事务接口
 */
public interface Transaction {

    void begin() throws MyOrmException; // 开启事务
    void commit() throws MyOrmException; // 提交事务
    void rollback() throws MyOrmException; // 回滚

}
