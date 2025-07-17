package com.example.demo.services.impl;

import com.example.demo.exceptions.MsisdnCheckException;
import com.example.demo.models.json.SubscriberProfile;
import com.example.demo.models.responses.MsisdnCheckResponse;
import com.example.demo.services.MegaBuilderService;
import com.example.demo.services.PersonificationService;
import com.example.demo.services.PrePersonificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PrePersonificationServiceImpl implements PrePersonificationService {
    private final MegaBuilderService megaBuilderService;
    private final PersonificationService personificationService;

    public PrePersonificationServiceImpl(MegaBuilderService megaBuilderService, PersonificationService personificationService) {
        this.megaBuilderService = megaBuilderService;
        this.personificationService = personificationService;
    }

    @Override
    public Long getSubsIdByMsisdn(String msisdn) {
        SubscriberProfile subscriberProfile = megaBuilderService.getSubscriberProfile(msisdn);
        boolean isType1 = subscriberProfile.getType() == 1;
        if (isType1) {
            return subscriberProfile.getSubsId();
        } else {
            log.error("Could not extract subsId for msisdn = {}; subsProfile: {} ", msisdn, subscriberProfile);
            //Абонент не найден!!!
            throw new MsisdnCheckException(subscriberProfile.getErrorMessage());
        }
    }

    @Override
    public MsisdnCheckResponse checkMsisdn(String token, int langId, String msisdn) {
        // Существует абонент или нет
        Long subsId = getSubsIdByMsisdn(msisdn);

        // Существует ли заявка абонента
        checkRequestCount(subsId, msisdn, langId);

        // Закреплен ли номер за промотером

        // Проверка категории номера

        String message = "По данному номеру " + msisdn + "заявок не найдено";

        return MsisdnCheckResponse.builder()
                .subsId(subsId)
                .message(message)
                .build();
    }

    private void checkRequestCount(Long subsId, String msisdn, int langId) {
        // check if request with the given subsId exists and passed further
        int passedCount = personificationService.countPassedRequestsBySubs(subsId);
    }
}
