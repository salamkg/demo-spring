package com.example.demo.services;

import com.example.demo.models.responses.MsisdnCheckResponse;
import com.example.demo.models.responses.PersonificationRequestResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//@Service
public interface PersonificationService {
    MsisdnCheckResponse checkMsisdn(String token, String msisdn);

    PersonificationRequestResponse createForIDCard(String token, String msisdn, String pin, String firstName,
                                                   String passportSeries, String passportNumber, MultipartFile documentOwner,
                                                   MultipartFile passportFront, MultipartFile passportBack);
}
