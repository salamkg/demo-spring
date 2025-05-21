package com.example.demo.services.impl;

import com.example.demo.models.json.PromoterSkppData;
import com.example.demo.models.responses.SimMovement;
import com.example.demo.services.BuilderFeignService;
import com.example.demo.services.BuilderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BuilderServiceImpl implements BuilderService {
    @Autowired
    private BuilderFeignService builderFeignService;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public SimMovement getSimMovement(String token, Long subsId) {
        return null;
    }

    @Override
    public PromoterSkppData getPromoterFromSkpp(String token) {
        Map<String, String> params = new HashMap<>();
        params.put("id", "79"); // retrieves id from microservice "${micro.megabuilder-service-v2.skpp-service-id}"
        params.put("action", "promoterSkppServiceAction");
        params.put("token", token);
        Object responseJson = builderFeignService.doRequestModified(params);
        PromoterSkppData promoterSkppData = objectMapper.convertValue(responseJson, PromoterSkppData.class);
        return promoterSkppData;
    }
}
