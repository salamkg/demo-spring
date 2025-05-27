package com.example.demo.exceptions;

public class SupervisorException extends RuntimeException {
    public SupervisorException(String supervisorNotSaved) {
        super(supervisorNotSaved);
    }
}
