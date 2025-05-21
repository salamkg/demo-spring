package com.example.demo.services;

import java.util.Map;

public interface BuilderFeignService {
    Object doRequestModified(Map<String, String> params);
}
