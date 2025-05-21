package com.example.demo.services.impl;

import com.example.demo.exceptions.MsisdnCheckException;
import com.example.demo.models.responses.InfocomPassportData;
import com.example.demo.models.responses.MsisdnCheckResponse;
import com.example.demo.models.responses.PersonificationRequestResponse;
import com.example.demo.models.responses.SimMovement;
import com.example.demo.services.BuilderService;
import com.example.demo.services.InfocomService;
import com.example.demo.services.PersonificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class PersonificationServiceImpl implements PersonificationService {

    @Autowired
    private BuilderService builderService;
    @Autowired
    private InfocomService infocomService;

    @Override
    public MsisdnCheckResponse checkMsisdn(String token, String msisdn) {
        checkSimMovement(token, msisdn);

        return MsisdnCheckResponse.builder()
                .message("Check Msisdn")
                .subsId(1L)
                .build();
    }

    @Override
    public PersonificationRequestResponse createForIDCard(String token, String msisdn, String pin, String firstName,
                                                          String passportSeries, String passportNumber, MultipartFile documentOwner,
                                                          MultipartFile passportFront, MultipartFile passportBack) {
        checkMsisdn(token, msisdn);

        InfocomPassportData infocomPassportData = infocomService.getPassportData(msisdn, pin, passportSeries, passportNumber);


        return null;
    }

    public void checkSimMovement(String token, String msisdn) {
        if (token != null && !token.isEmpty()) {
            Long subsId = 1L;
            SimMovement simMovement = builderService.getSimMovement(token, subsId);
            if (simMovement.getCode() == 0) {
                log.error("SimMovement for msisdn = {}: {}", msisdn, simMovement);
                throw new MsisdnCheckException("Msisdn is not assigned to promoter");
            }
        } else {
            throw new NullPointerException("Token is empty");
        }
    }

}
