package com.example.demo.services;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface ClientFeign {

    @PostMapping("/api/get/passport-data")
    Object getPassportData(@RequestBody Map<String, String> model, @RequestParam(name = "msisdn") String msisdn, @RequestParam(name = "source") String source);
}
