package com.example.demo.services;

import com.example.demo.models.dto.SupervisorDTO;
import com.example.demo.models.json.SupervisorSkppData;

public interface SupervisorService {
    SupervisorSkppData findSupervisorFromSkppByPromoterId(Long skppPromoterId);

    SupervisorDTO createSupervisorFromSkppData(SupervisorSkppData supervisorSkppData);
}
