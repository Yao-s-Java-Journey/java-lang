package com.exception.custom;

/**
 * 自定义运行时异常
 */
public class CustomRuntimeException extends RuntimeException {
    public CustomRuntimeException() {}

    public CustomRuntimeException(String message) {
        super(message);
    }

}
