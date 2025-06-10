package com.example.demo;

import com.example.demo.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
class DemoApplicationTests {

    @Mock
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testFindUser() {
        when(userRepository.findById(1L)).thenReturn(userRepository.findById(1L));
    }

}
