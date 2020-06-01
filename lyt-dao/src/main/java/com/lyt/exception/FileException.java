package com.lyt.exception;

/**
 * @ClassName FileException
 * @Description file的处理异常信息
 * @Author liyintao
 * @Date 2020/6/1 18:22
 */
public class FileException extends RuntimeException{
    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}