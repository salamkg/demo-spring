package com.example.demo.services.impl;

import com.example.demo.dao.PromoterRepository;
import com.example.demo.exceptions.NoDataFoundException;
import com.example.demo.exceptions.PromoterException;
import com.example.demo.models.dto.PromoterDTO;
import com.example.demo.models.dto.SupervisorDTO;
import com.example.demo.models.entities.Promoter;
import com.example.demo.models.json.PromoterSkppData;
import com.example.demo.models.json.SupervisorSkppData;
import com.example.demo.services.BuilderService;
import com.example.demo.services.PromoterService;
import com.example.demo.services.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PromoterServiceImpl implements PromoterService {
    @Autowired
    private BuilderService builderService;
    @Autowired
    private PromoterRepository promoterRepository;
    @Autowired
    private SupervisorService supervisorService;

    @Override
    public PromoterSkppData findPromoterFromSkppByMsisdn(String msisdn) {
        if (msisdn == null || msisdn.isEmpty()) {
            throw new NullPointerException("msisdn is null or empty");
        }
        try {
            PromoterSkppData promoterSkppData = builderService.getPromoterFromSkpp(msisdn);
            return promoterSkppData;
        } catch (Exception e) {
            throw new NoDataFoundException(e.getMessage());
        }
    }

    @Override
    public PromoterDTO createPromoterFromSkppData(PromoterSkppData promoterSkppData) {
        String promoterNotSavedMessage = "PromoterNotSavedMessage";

        if (promoterSkppData == null) throw new PromoterException(promoterNotSavedMessage);

        try {
            Optional<Promoter> promoterFromSkpp = promoterRepository.findPromoterBySkppPromoterId(21L);
            if (promoterFromSkpp.isPresent()) {
                Promoter promoter = promoterFromSkpp.get();

                // Find Supervisor by Promoter Id
                SupervisorSkppData supervisorSkppData = supervisorService.findSupervisorFromSkppByPromoterId(promoter.getId());
                SupervisorDTO supervisorDTO = supervisorService.createSupervisorFromSkppData(supervisorSkppData);
            }

            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
