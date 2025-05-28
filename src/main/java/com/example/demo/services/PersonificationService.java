package com.example.demo.services;

import com.example.demo.models.dto.PersonificationRequestDTO;
import com.example.demo.models.responses.MsisdnCheckResponse;
import com.example.demo.models.responses.PersonificationRequestResponse;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

//@Service
public interface PersonificationService {
    MsisdnCheckResponse checkMsisdn(String token, String msisdn);

    PersonificationRequestResponse createForIDCard(String token, String msisdn, String pin, String firstName,
                                                   String passportSeries, String passportNumber, MultipartFile documentOwner,
                                                   MultipartFile passportFront, MultipartFile passportBack, List<String> childNumbers, Long groupId);

    String scanPassport(MultipartFile passportFile);

    PersonificationRequestDTO findById(Long personificationRequestId);
}
