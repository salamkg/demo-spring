package com.example.demo.mappers;

import com.example.demo.models.entities.Promoter;
import com.example.demo.models.json.PromoterSkppData;
import org.springframework.stereotype.Component;

@Component
public class PromoterMapper {

    public PromoterSkppData toPromoterSkppData(Promoter promoter) {
        PromoterSkppData.PromoterSkppDataBuilder promoterSkppDataBuilder = PromoterSkppData.builder();
        return promoterSkppDataBuilder
                .id(promoter.getId())
                .inn(promoter.getInn())
                .name(promoter.getPromoterName())
                .msisdn(promoter.getMsisdn())
                .p_number(promoter.getPromoterNumber())
                .build();
    }
}
