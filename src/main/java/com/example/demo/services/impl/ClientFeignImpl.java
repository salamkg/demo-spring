package com.example.demo.services.impl;

import com.example.demo.services.ClientFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ClientFeignImpl implements ClientFeign {

    @Override
    public Object getPassportData(Map<String, String> model, String msisdn, String source) {
        Map<String, String> passportData = new HashMap<>();
        passportData.put("msisdn", msisdn);
        passportData.put("source", source);
        passportData.put("firstName", "John");
        passportData.put("lastName", "Smith");
        passportData.put("passportNumber", "AASDSAD2123");

        return passportData;
    }
}
