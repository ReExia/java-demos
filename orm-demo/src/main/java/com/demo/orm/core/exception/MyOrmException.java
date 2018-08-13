package com.demo.orm.core.exception;

/**
 * @author setsuna
 * 自定义数据库异常
 */
public class MyOrmException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MyOrmException() {
        super();
    }

    public MyOrmException(String message) {
        super(message);
    }

    public MyOrmException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyOrmException(Throwable cause) {
        super(cause);
    }

}
