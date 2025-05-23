package com.example.demo.services.impl;

import com.example.demo.services.PrePersonificationService;
import org.springframework.stereotype.Service;

@Service
public class PrePersonificationServiceImpl implements PrePersonificationService {
    @Override
    public Long getSubsIdByMsisdn(String msisdn) {
        return 0L;
    }
}
