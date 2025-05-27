package com.example.demo.services.impl;

import com.example.demo.dao.SupervisorRepository;
import com.example.demo.exceptions.SupervisorException;
import com.example.demo.mappers.SupervisorMapper;
import com.example.demo.models.dto.SupervisorDTO;
import com.example.demo.models.entities.Supervisor;
import com.example.demo.models.json.SupervisorSkppData;
import com.example.demo.services.SupervisorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupervisorServiceImpl implements SupervisorService {
    @Autowired
    private SupervisorRepository supervisorRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SupervisorMapper supervisorMapper;

    @Override
    public SupervisorSkppData findSupervisorFromSkppByPromoterId(Long promoterId) {
        Supervisor supervisor = supervisorRepository.findByPromoters_Id(promoterId);
        SupervisorSkppData supervisorSkppData = objectMapper.convertValue(supervisor, SupervisorSkppData.class);
        return supervisorSkppData;
    }

    @Override
    public SupervisorDTO createSupervisorFromSkppData(SupervisorSkppData supervisorSkppData) {

        if (supervisorSkppData == null) {
            throw new SupervisorException("Supervisor Not Saved");
        }

        try {
            Optional<Supervisor> supervisorFromSkpp = supervisorRepository.findSupervisorBySkppSupervisorId(supervisorSkppData.getId());
            if (supervisorFromSkpp.isPresent()) {
                Supervisor supervisor = supervisorFromSkpp.get();
                supervisor.setSupervisorName(supervisorSkppData.getName());
                supervisor.setMsisdn(supervisorSkppData.getMsisdn());
                supervisor.setSubsId(supervisorSkppData.getSubs());
                supervisor = supervisorRepository.save(supervisor);

                SupervisorDTO savedSupervisorDTO = supervisorMapper.toSupervisorDTO(supervisor);
                return savedSupervisorDTO;
            } else {
                SupervisorDTO supervisorDTO = SupervisorDTO.builder()
                        .skppSupervisorId(supervisorSkppData.getId())
                        .supervisorName(supervisorSkppData.getName())
                        .msisdn(supervisorSkppData.getMsisdn())
                        .subsId(supervisorSkppData.getSubs())
                        .build();

                Supervisor supervisor = supervisorMapper.toSupervisor(supervisorDTO);
                supervisor = supervisorRepository.save(supervisor);
                return supervisorMapper.toSupervisorDTO(supervisor);
            }
        } catch (Exception e) {
            throw new SupervisorException("Supervisor Not Saved");
        }
    }
}
