package com.example.demo.services;

import com.example.demo.models.json.PromoterSkppData;
import com.example.demo.models.json.SubscriberProfile;
import com.example.demo.models.responses.SimMovement;

public interface MegaBuilderService {

    SimMovement getSimMovement(String token, Long subsId);

    PromoterSkppData getPromoterFromSkpp(String msisdn);

    SubscriberProfile getSubscriberProfile(String msisdn);
}
