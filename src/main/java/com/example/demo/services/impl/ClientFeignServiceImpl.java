package com.example.demo.services.impl;

import com.example.demo.services.ClientFeignService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ClientFeignServiceImpl implements ClientFeignService {
    @Override
    public Object getPassportData(Map<String, String> model, String msisdn, String source) {
        return null;
    }
}
