package com.example.demo.services.impl;

import com.example.demo.models.json.PromoterSkppData;
import com.example.demo.services.BuilderFeignService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BuilderFeignServiceImpl implements BuilderFeignService {
    @Override
    public Object doRequestModified(Map<String, String> params) {
        PromoterSkppData promoterSkppData = new PromoterSkppData();
        params.put("id", "1");
        params.put("name", "Promoter Test Name");
        params.put("msisdn", "996555395504");
        params.put("subs", "123");
        params.put("inn", "21207201401054");
        params.put("p_number", "123");
        return params;
    }
}
