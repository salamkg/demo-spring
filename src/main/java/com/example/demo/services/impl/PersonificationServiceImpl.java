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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
    public String scanPassport(MultipartFile file) {
        try {
            File convertFile = convertToFile(file);
            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath("D:\\Users\\smadankulov\\IdeaProjects\\langs"); // путь к языковым моделям
            tesseract.setLanguage("rus+eng");  // для распознавания рус+англ текста

            return tesseract.doOCR(convertFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private File convertToFile(MultipartFile multipartFile) throws IOException {
        File tempFile = File.createTempFile("upload-", ".jpg");
        multipartFile.transferTo(tempFile);
        return tempFile;
    }

    @Override
    public PersonificationRequestResponse createForIDCard(String token, String msisdn, String pin, String firstName,
                                                          String passportSeries, String passportNumber, MultipartFile documentOwner,
                                                          MultipartFile passportFront, MultipartFile passportBack) {
//        checkMsisdn(token, msisdn);
        InfocomPassportData infocomPassportData = infocomService.getPassportData(msisdn, pin, passportSeries, passportNumber);
        System.out.println(infocomPassportData);

        // Promoter
        PromoterSkppData promoterSkppData = promoterService.findPromoterFromSkppByToken(token);
        PromoterDTO promoterDTO = promoterService.createPromoterFromSkppData(promoterSkppData);
        System.out.println(promoterSkppData);
        return null;
    }

    public Map<String, String> convertJsonToMap(String objStr) {
        Map<String, String> params = new HashMap<>();
        if (objStr != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                params = mapper.readValue(objStr, new TypeReference<>() {
                });
            } catch (Exception e) {
                System.out.println("Error converting JSON to Map : " + e.getMessage());
            }
        }
        return params;
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
