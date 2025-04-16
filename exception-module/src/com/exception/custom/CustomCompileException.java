package com.exception.custom;

/**
 * 自定义编译异常
 */
public class CustomCompileException extends Exception {
    public CustomCompileException() {
    }

    public CustomCompileException(String message) {
        super(message);
    }
}
