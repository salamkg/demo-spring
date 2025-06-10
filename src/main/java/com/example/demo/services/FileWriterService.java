package com.example.demo.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileWriterService {
    String writeFile(MultipartFile textFile);
}
