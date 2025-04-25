package com.example.demo.services;

import com.example.demo.models.responses.InfocomPassportData;

public interface InfocomService {
    InfocomPassportData getPassportData(String msisdn, String pin, String passportSeries, String passportNumber);
}
