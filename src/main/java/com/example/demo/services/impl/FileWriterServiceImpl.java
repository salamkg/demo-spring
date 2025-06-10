package com.example.demo.services.impl;

import com.example.demo.models.dto.UserDTO;
import com.example.demo.models.entities.notify.Store;
import com.example.demo.models.entities.sql.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.FileWriterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;


@Slf4j
@Service
public class FileWriterServiceImpl implements FileWriterService {

    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private UserRepository userRepository;

    @Override
    public String writeFile(MultipartFile textFile) {
        try {
            // Path to write
            Path outputPath = Paths.get("test.txt");
            Files.write(outputPath, textFile.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            List<User> users = userRepository.findAll();
            UserDTO userDTO = new UserDTO();
            List<UserDTO> userDTOs = new ArrayList<>();
            Map<User, String> map = new HashMap<>();

            users.forEach(user -> {
                User user1 = new User();
                user1.setUsername("User1");

                try {
                    User user11 = (User) user1.clone();
//                    System.out.println("Cloned: " + user11);
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
                User user2 = new User();
                user2.setUsername("User1");
                map.put(user1, "admin");
                map.put(user2, "user");

//                System.out.println(user1.equals(user2));

                //System.out.println(user); // User{id=1, username='Alice', email='alice@mail.com', createdAt=2020-01-01 00:00:00.0'}

//                System.out.println(map.get(user1));
//                System.out.println(map.get(user2));

                userDTO.setId(user.getId());
                userDTO.setCreatedAt(user.getCreatedAt());
                userDTO.setEmail(user.getEmail());
                userDTO.setUsername(user.getUsername());
                userDTOs.add(userDTO);
            });
//            log.info("User DTO: {} {}", "{" + userDTO.getId(), userDTO.getUsername() + "}");

            byte[] bytes = "Hello World".getBytes();
            InputStream inputStream = new ByteArrayInputStream(bytes);
            int b;
            while ((b = inputStream.read()) != -1) {
                System.out.println((char) b);
            }

            return "File written successfully";
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return "File write fail";
        }
    }

}
