package com.example.demo.services.impl;

import com.example.demo.exceptions.MsisdnCheckException;
import com.example.demo.models.dto.PersonificationRequestDTO;
import com.example.demo.models.dto.PromoterDTO;
import com.example.demo.models.enums.DocumentType;
import com.example.demo.models.json.PromoterSkppData;
import com.example.demo.models.responses.InfocomPassportData;
import com.example.demo.models.responses.MsisdnCheckResponse;
import com.example.demo.models.responses.PersonificationRequestResponse;
import com.example.demo.models.responses.SimMovement;
import com.example.demo.services.BuilderService;
import com.example.demo.services.InfocomService;
import com.example.demo.services.PersonificationService;
import com.example.demo.services.PromoterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class PersonificationServiceImpl implements PersonificationService {

    @Autowired
    private BuilderService builderService;
    @Autowired
    private InfocomService infocomService;
    @Autowired
    private PromoterService promoterService;

    @Override
    public MsisdnCheckResponse checkMsisdn(String token, String msisdn) {
        checkSimMovement(token, msisdn);

        return MsisdnCheckResponse.builder()
                .message("Check Msisdn")
                .subsId(1L)
                .build();
    }

    public PersonificationRequestDTO create(String msisdn, DocumentType documentType, PromoterDTO promoterDTO,
                                            List<String> childNumbers, Long groupId) {
        PersonificationRequestDTO personificationRequestDTO = new PersonificationRequestDTO();
        personificationRequestDTO.setMsisdn(msisdn);
        personificationRequestDTO.setSubsId(1L);
        personificationRequestDTO.setDocumentType(documentType);
        personificationRequestDTO.setPersonificationRequestUUId(UUID.randomUUID());
        personificationRequestDTO.setPromoterDTO(promoterDTO);
        personificationRequestDTO.setCreatDate(new Date());

        return personificationRequestDTO;
    }

    @Override
    public PersonificationRequestResponse createForIDCard(String token, String msisdn, String pin, String firstName,
                                                          String passportSeries, String passportNumber, MultipartFile documentOwner,
                                                          MultipartFile passportFront, MultipartFile passportBack) {
//        checkMsisdn(token, msisdn);

        InfocomPassportData infocomPassportData = infocomService.getPassportData(msisdn, pin, passportSeries, passportNumber);
        System.out.println(infocomPassportData);
        //Find Promoter By token
        PromoterSkppData promoterSkppData = promoterService.findPromoterFromSkppByToken(token);
        System.out.println(promoterSkppData);
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
