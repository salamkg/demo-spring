package com.example.demo.services;

import com.example.demo.models.responses.MsisdnCheckResponse;

public interface PrePersonificationService {
    Long getSubsIdByMsisdn(String msisdn);

    MsisdnCheckResponse checkMsisdn(String token, int langId, String msisdn);
}
