package com.group13.cog.exception;

/**
 * Created by Yiran on 2020/3/2.
 */
public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String msg) {
        super(msg);
    }

    public StorageFileNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
