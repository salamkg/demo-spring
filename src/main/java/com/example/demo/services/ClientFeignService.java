package com.example.demo.services;

import java.util.Map;

public interface ClientFeignService {
    Object getPassportData(Map<String, String> model, String msisdn, String source);
}
