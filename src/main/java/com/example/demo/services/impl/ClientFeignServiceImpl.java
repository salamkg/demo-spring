package com.example.demo.services.impl;

import com.example.demo.services.ClientFeignService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ClientFeignServiceImpl implements ClientFeignService {
    @Override
    public Object getPassportData(Map<String, String> model, String msisdn, String source) {
        model.put("pin", "21207201401054");
        model.put("name", "John");
        model.put("surname", "Doe");
        model.put("gender", "M");
        model.put("dateOfBirth", "2014-07-12");
        model.put("passportSeries", "ID");
        model.put("passportNumber", "0271404");
        model.put("passportAuthority", "mkk");
        return model;
    }
}
