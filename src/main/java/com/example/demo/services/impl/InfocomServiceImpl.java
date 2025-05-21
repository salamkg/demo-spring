package com.example.demo.services.impl;

import com.example.demo.models.responses.InfocomPassportData;
import com.example.demo.services.ClientFeign;
import com.example.demo.services.ClientFeignService;
import com.example.demo.services.InfocomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class InfocomServiceImpl implements InfocomService {

    @Autowired
    private ClientFeign clientFeign;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ClientFeignService clientFeignService;

    @Override
    public InfocomPassportData getPassportData(String msisdn, String pin, String passportSeries, String passportNumber) {
        Map<String, String> model = new HashMap<>();
        model.put("pin", pin);
        model.put("series", passportSeries);
        model.put("number", passportNumber);
        model.put("msisdn", msisdn);
        log.info("Sending infocom passport data");

        //microservice
        String infocomRequest = "data";

        Object responseJson = clientFeignService.getPassportData(model, msisdn, infocomRequest);
        InfocomPassportData infocomPassportData = objectMapper.convertValue(responseJson, InfocomPassportData.class);
        log.info("Infocom passport data received: {}", infocomPassportData);

        return infocomPassportData;
    }
}
