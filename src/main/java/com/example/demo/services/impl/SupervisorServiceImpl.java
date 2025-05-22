package com.example.demo.services.impl;

import com.example.demo.models.dto.SupervisorDTO;
import com.example.demo.models.json.SupervisorSkppData;
import com.example.demo.services.SupervisorService;
import org.springframework.stereotype.Service;

@Service
public class SupervisorServiceImpl implements SupervisorService {
    @Override
    public SupervisorSkppData findSupervisorFromSkppByPromoterId(Long skppPromoterId) {
        return null;
    }

    @Override
    public SupervisorDTO createSupervisorFromSkppData(SupervisorSkppData supervisorSkppData) {
        return null;
    }
}
