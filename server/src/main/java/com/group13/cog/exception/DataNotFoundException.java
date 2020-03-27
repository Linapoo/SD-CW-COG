package com.group13.cog.exception;

/**
 * Created by Yiran on 2020/3/3.
 */

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String msg) {
        super(msg);
    }
}
