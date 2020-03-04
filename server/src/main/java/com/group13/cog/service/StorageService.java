package com.group13.cog.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Yiran on 2020/3/2.
 */
public interface StorageService {
    /**
     * Store the file using original filename.
     * Throw {@link com.group13.cog.exception.StorageException} if the operation failed.
     *
     * @param file The file
     */
    void store(MultipartFile file);

    /**
     * Store the file using the filename.
     * Throw {@link com.group13.cog.exception.StorageException} if the operation failed.
     *
     * @param file     The file
     * @param filename The filename
     */
    void store(MultipartFile file, String filename);

    /**
     * Load a file from the server.
     * Throw {@link com.group13.cog.exception.StorageFileNotFoundException} if this file doesn't exist.
     *
     * @param filename The filename
     * @return A file resource
     */
    Resource loadAsResource(String filename);
}
