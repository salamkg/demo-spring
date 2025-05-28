package com.example.demo.services.impl;

import com.example.demo.models.dto.PersonificationRequestDTO;
import com.example.demo.models.responses.SendPersonificationDataResponse;
import com.example.demo.services.PersonificationService;
import com.example.demo.services.RequestProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RequestProcessingServiceImpl implements RequestProcessingService {
    @Autowired
    private PersonificationService personificationService;

    @Override
    public SendPersonificationDataResponse submitRequest(Long personificationRequestId, MultipartFile signature,
                                                         String keyWord, String email, String promoterComment) {
        if (signature.isEmpty()) throw new RuntimeException("Signature is empty");
        PersonificationRequestDTO personificationRequestDTO = personificationService.findById(personificationRequestId);

        return null;
    }
}
