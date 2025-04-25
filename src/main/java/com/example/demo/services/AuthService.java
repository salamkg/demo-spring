package com.example.demo.services;

import com.example.demo.models.responses.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    LoginResponse login(String version, String msisdn, String device, int langId, String pinCode);
}
