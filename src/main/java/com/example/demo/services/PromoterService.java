package com.example.demo.services;


import com.example.demo.models.dto.PromoterDTO;
import com.example.demo.models.json.PromoterSkppData;

public interface PromoterService {
    PromoterSkppData findPromoterFromSkppByMsisdn(String token);

    PromoterDTO createPromoterFromSkppData(PromoterSkppData promoterSkppData);
}
