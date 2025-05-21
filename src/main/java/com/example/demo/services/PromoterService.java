package com.example.demo.services;


import com.example.demo.models.json.PromoterSkppData;

public interface PromoterService {
    PromoterSkppData findPromoterFromSkppByToken(String token);
}
