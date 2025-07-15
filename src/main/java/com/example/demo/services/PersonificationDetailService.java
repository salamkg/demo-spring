package com.example.demo.services;

import com.example.demo.models.dto.PersonificationRequestDTO;
import com.example.demo.models.dto.PersonificationRequestDetailDTO;
import org.springframework.web.multipart.MultipartFile;

public interface PersonificationDetailService {
    PersonificationRequestDetailDTO create(PersonificationRequestDTO personificationRequestDTO, String pin,
                                           String passportSeries, String passportNumber);

    PersonificationRequestDetailDTO addFiles(PersonificationRequestDetailDTO personificationRequestDetailDTO,
                                             MultipartFile docOwner, MultipartFile passFront, MultipartFile passBack);

    PersonificationRequestDetailDTO findByPersonificationRequestId(Long personificationRequestId);
}
