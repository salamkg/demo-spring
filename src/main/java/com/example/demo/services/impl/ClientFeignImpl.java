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
        passportData.put("firstName", model.get("firstName"));
        passportData.put("lastName", model.get("lastName"));
        passportData.put("passportNumber", model.get("passportNumber"));
        passportData.put("passportSeries", model.get("passportSeries"));
        passportData.put("pin", model.get("pin"));

        return passportData;
    }
}
