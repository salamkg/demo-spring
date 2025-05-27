package com.example.demo.mappers;

import com.example.demo.models.dto.SupervisorDTO;
import com.example.demo.models.entities.Supervisor;
import org.springframework.stereotype.Component;

@Component
public class SupervisorMapper {
    public SupervisorDTO toSupervisorDTO(Supervisor supervisor) {
        if (supervisor == null) { return null; }

        SupervisorDTO supervisorDTO = new SupervisorDTO();
        supervisorDTO.setSupervisorName(supervisor.getSupervisorName());
        supervisorDTO.setSkppSupervisorId(supervisor.getSkppSupervisorId());
        supervisorDTO.setMsisdn(supervisor.getMsisdn());
        supervisorDTO.setDealer(supervisor.getDealer());
        supervisorDTO.setCreateDate(supervisor.getCreateDate());
        supervisorDTO.setSubsId(supervisor.getSubsId());
        return supervisorDTO;
    }

    public Supervisor toSupervisor(SupervisorDTO supervisorDTO) {
        if (supervisorDTO == null) { return  null; }

        Supervisor supervisor = new Supervisor();
        supervisor.setSupervisorName(supervisorDTO.getSupervisorName());
        supervisor.setSkppSupervisorId(supervisorDTO.getSkppSupervisorId());
        supervisor.setMsisdn(supervisorDTO.getMsisdn());
        supervisor.setDealer(supervisorDTO.getDealer());
        supervisor.setCreateDate(supervisorDTO.getCreateDate());
        supervisor.setSubsId(supervisorDTO.getSubsId());
        return supervisor;
    }
}
