package com.group13.cog.service.impl;

import com.group13.cog.exception.StorageException;
import com.group13.cog.exception.StorageFileNotFoundException;
import com.group13.cog.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by Yiran on 2020/3/2.
 */

@Service
public class StorageServiceImpl implements StorageService {

    private final Path rootLocation;
    
    @Autowired
    public StorageServiceImpl() {
        this.rootLocation = Paths.get("upload-files/avatar");
        initDir(this.rootLocation);
    }

    private void initDir(Path path){
        if(!Files.exists(path)){
            try{
                Files.createDirectories(path);
            }catch(IOException e){
                throw new StorageException("Failed to make directories " + path, e);
            }
        }
    }
    /**@Override
    public void storeImage(MultipartFile file, String filename, String oldname){
        
    } 
    @Override
    public void delete(String filename)
**/
    @Override
    public void store(MultipartFile file) {
        store(file, null);
    }

    @Override
    public void store(MultipartFile file, String filename) {
        if (filename == null)
            filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
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
