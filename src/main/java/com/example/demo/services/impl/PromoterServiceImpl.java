package com.example.demo.services.impl;

import com.example.demo.exceptions.NoDataFoundException;
import com.example.demo.models.json.PromoterSkppData;
import com.example.demo.services.BuilderService;
import com.example.demo.services.PromoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromoterServiceImpl implements PromoterService {
    @Autowired
    private BuilderService builderService;

    @Override
    public PromoterSkppData findPromoterFromSkppByToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new NullPointerException("token is null or empty");
        }
        try {
            PromoterSkppData promoterSkppData = builderService.getPromoterFromSkpp(token);
            return promoterSkppData;
        } catch (Exception e) {
            throw new NoDataFoundException(e.getMessage());
        }
    }
}
