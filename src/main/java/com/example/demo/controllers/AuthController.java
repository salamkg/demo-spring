package com.example.demo.controllers;

import com.example.demo.models.requests.LoginRequest;
import com.example.demo.models.responses.LoginResponse;
import com.example.demo.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestHeader(required = false, name = "app-version") String version, @RequestBody LoginRequest request) {
         return ResponseEntity.ok(authService.login(version, request.getMsisdn(), request.getDevice(), request.getLangId(), request.getPinCode()));
    }
}
