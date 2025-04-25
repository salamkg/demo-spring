package com.example.demo.models.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginRequest {
    private String msisdn;
    private String device;
    private int langId;
    private String pinCode;
}
