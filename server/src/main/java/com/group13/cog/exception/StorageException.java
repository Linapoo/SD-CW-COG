package com.group13.cog.exception;

/**
 * Created by Yiran on 2020/3/2.
 */
public class StorageException extends RuntimeException {

    public StorageException(String msg) {
        super(msg);
    }

    public StorageException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
