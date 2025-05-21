package com.example.demo.services;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "${passport-client.name}", url = "http://localhost:8080")
public interface ClientFeign {

    @PostMapping("/api/get/passport-data")
    Object getPassportData(@RequestBody Map<String, String> model, @RequestParam(name = "msisdn") String msisdn, @RequestParam(name = "source") String source);
}
