package com.example.demo.services.impl;

import com.example.demo.models.responses.SimMovement;
import com.example.demo.services.BuilderService;
import org.springframework.stereotype.Service;

@Service
public class BuilderServiceImpl implements BuilderService {
    @Override
    public SimMovement getSimMovement(String token, Long subsId) {
        return null;
    }
}
