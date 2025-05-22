package com.example.demo.services;

import com.example.demo.models.json.PromoterSkppData;
import com.example.demo.models.responses.SimMovement;

public interface BuilderService {

    SimMovement getSimMovement(String token, Long subsId);

    PromoterSkppData getPromoterFromSkpp(String msisdn);
}
