package com.example.demo.services.impl;

import com.example.demo.models.dto.PersonificationRequestDTO;
import com.example.demo.models.dto.PersonificationRequestDetailDTO;
import com.example.demo.services.PersonificationDetailService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PersonificationDetailServiceImpl implements PersonificationDetailService {
    @Override
    public PersonificationRequestDetailDTO create(PersonificationRequestDTO personificationRequestDTO, String pin,
                                                  String passportSeries, String passportNumber) {
        return null;
    }

    @Override
    public PersonificationRequestDetailDTO addFiles(PersonificationRequestDetailDTO personificationRequestDetailDTO,
                                                    MultipartFile docOwner, MultipartFile passFront, MultipartFile passBack) {
        return null;
    }

    @Override
    public PersonificationRequestDetailDTO findByPersonificationRequestId(Long personificationRequestId) {
        return null;
    }
}
