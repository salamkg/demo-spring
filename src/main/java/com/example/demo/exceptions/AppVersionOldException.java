package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AppVersionOldException extends ResponseStatusException {
    public AppVersionOldException(String reason) {
        super(HttpStatus.NOT_ACCEPTABLE, reason);
    }

    @Override
    public String getMessage() {
        return getReason();
    }
}
