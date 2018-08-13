package com.example.core.exception;

/**
 * 自定义异常
 */
public class MyOrmException extends RuntimeException {

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
