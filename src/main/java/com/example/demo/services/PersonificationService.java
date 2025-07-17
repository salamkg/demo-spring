package com.example.demo.services;

import com.example.demo.models.dto.PersonificationRequestDTO;
import com.example.demo.models.enums.DocumentType;
import com.example.demo.models.responses.MsisdnCheckResponse;
import com.example.demo.models.responses.PersonificationRequestResponse;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

//@Service
public interface PersonificationService {
    MsisdnCheckResponse checkMsisdn(String token, String msisdn);

    PersonificationRequestResponse createForIDCard(String token, String msisdn, String pin, String firstName,
                                                   String passportSeries, String passportNumber, MultipartFile documentOwner,
                                                   MultipartFile passportFront, MultipartFile passportBack, List<String> childNumbers, Long groupId);

    PersonificationRequestResponse createForOtherDoc(String token, String msisdn, String surname, String name, String middleName,
                                                     Date dateOfBirth, String passportSeries, String passportNumber, String pin,
                                                     String organization, Date issueDate, String gender, DocumentType documentType,
                                                     MultipartFile documentFront, MultipartFile documentBack, MultipartFile documentOwner,
                                                     List<String> childNumbers, Long groupId, int langId);

    String scanPassport(MultipartFile passportFile);

    PersonificationRequestDTO findById(Long personificationRequestId);

    int countPassedRequestsBySubs(Long subsId);
}
