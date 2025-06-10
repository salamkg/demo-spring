package com.example.demo.controllers;

import com.example.demo.services.FileWriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    @Autowired
    private FileWriterService fileWriterService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            Path path = Paths.get(System.getProperty("user.dir"), fileName);
            Files.write(path, file.getBytes());

            Map<String, Object> result = Map.of(
                    "status", "ok",
                    "message", "File uploaded successfully!",
                    "path", path.toAbsolutePath().toString()
            );
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("status", "error", "message", e.getMessage()));
        }
    }

    @PostMapping("/file-write")
    public ResponseEntity<?> writeFile(@RequestParam("file") MultipartFile textFile) {
        fileWriterService.writeFile(textFile);


        return ResponseEntity.ok().build();
    }
}
