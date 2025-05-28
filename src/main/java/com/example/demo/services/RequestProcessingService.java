package com.example.demo.services;

import com.example.demo.models.responses.SendPersonificationDataResponse;
import org.springframework.web.multipart.MultipartFile;

public interface RequestProcessingService {
    SendPersonificationDataResponse submitRequest(Long personificationRequestId,
                                                  MultipartFile signature,
                                                  String keyWord,
                                                  String email,
                                                  String promoterComment);
}
