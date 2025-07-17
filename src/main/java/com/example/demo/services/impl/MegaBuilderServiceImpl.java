package com.example.demo.services.impl;

import com.example.demo.dao.PromoterRepository;
import com.example.demo.mappers.PromoterMapper;
import com.example.demo.models.entities.Promoter;
import com.example.demo.models.json.PromoterSkppData;
import com.example.demo.models.json.SubscriberProfile;
import com.example.demo.models.responses.SimMovement;
import com.example.demo.services.BuilderFeignService;
import com.example.demo.services.MegaBuilderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MegaBuilderServiceImpl implements MegaBuilderService {
    @Autowired
    private BuilderFeignService builderFeignService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PromoterRepository promoterRepository;
    @Autowired
    private PromoterMapper promoterMapper;

    @Override
    public SimMovement getSimMovement(String token, Long subsId) {
        return null;
    }

    @Override
    public PromoterSkppData getPromoterFromSkpp(String msisdn) {
        // TODO getting promoter directly from DB
        Promoter promoter = promoterRepository.findPromoterByMsisdn(msisdn);
        PromoterSkppData promoterSkppData = promoterMapper.toPromoterSkppData(promoter);

        // TODO this part is not used
        Map<String, String> params = new HashMap<>();
        params.put("id", "79"); // retrieves id from microservice "${micro.megabuilder-service-v2.skpp-service-id}"
        params.put("action", "promoterSkppServiceAction");
        params.put("token", "token");
        Object responseJson = builderFeignService.doRequestModified(params);
//        PromoterSkppData promoterSkppData = objectMapper.convertValue(responseJson, PromoterSkppData.class);
        return promoterSkppData;
    }

    @Override
    public SubscriberProfile getSubscriberProfile(String msisdn) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("id", "10");
            params.put("msisdn", msisdn);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
