package com.group13.cog.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import com.group13.cog.exception.StorageException;
import com.group13.cog.exception.StorageFileNotFoundException;

public class FileStorage {
    
    private final Path storagePath;

    public FileStorage(String dirPath) {
        this.storagePath = Paths.get(dirPath);
        initDir(this.storagePath);
    }

    private void initDir(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new StorageException("Failed to make directories " + path, e);
            }
        }
    }

    public void delete(String filename) {
        Path filePath = this.storagePath.resolve(filename);
        if (Files.exists(filePath)){
            try {
                Files.delete(filePath);
            } catch (IOException e) {
                throw new StorageException("Failed to delete file");
            }
        }
    }

    public void store(MultipartFile file, String filename){
        if (filename == null){
            throw new StorageException("No filename found in store");
        }
        try {
            if (file.isEmpty()){
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.storagePath.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e){
            throw new StorageException("Failed to store file" + filename, e);
        }
    }

    public Resource loadAsResource(String filename){
        try {
            Path file = this.storagePath.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }
}
