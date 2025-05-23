package com.example.demo.services.impl;

import com.example.demo.dao.SupervisorRepository;
import com.example.demo.models.dto.SupervisorDTO;
import com.example.demo.models.entities.Supervisor;
import com.example.demo.models.json.SupervisorSkppData;
import com.example.demo.services.SupervisorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupervisorServiceImpl implements SupervisorService {
    @Autowired
    private SupervisorRepository supervisorRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public SupervisorSkppData findSupervisorFromSkppByPromoterId(Long promoterId) {
        Supervisor supervisor = supervisorRepository.findByPromoters_Id(promoterId);
        SupervisorSkppData supervisorSkppData = objectMapper.convertValue(supervisor, SupervisorSkppData.class);
        return supervisorSkppData;
    }

    @Override
    public SupervisorDTO createSupervisorFromSkppData(SupervisorSkppData supervisorSkppData) {
        return null;
    }
}
