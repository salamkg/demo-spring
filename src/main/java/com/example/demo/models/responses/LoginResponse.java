package com.example.demo.models.responses;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginResponse {
    @NonNull
    private String result;
    @NonNull
    private String token;
    @NonNull
    private String role;
}
