package com.technicalsand.stream.audiovideo.springcontent;

import org.springframework.content.fs.config.EnableFilesystemStores;
import org.springframework.content.fs.io.FileSystemResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Configuration
@EnableFilesystemStores
public class ApplicationConfig {

    @Bean
    File filesystemRoot() {
        try {
            return Files.createTempDirectory("").toFile();
        } catch (IOException ioe) {}
        return null;
    }

    @Bean
    public FileSystemResourceLoader fileSystemResourceLoader() {
        return new FileSystemResourceLoader(filesystemRoot().getAbsolutePath());
    }

}