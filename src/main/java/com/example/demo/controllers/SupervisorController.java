package com.example.demo.controllers;

import com.example.demo.models.dto.SupervisorDTO;
import com.example.demo.models.json.SupervisorSkppData;
import com.example.demo.services.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/supervisors")
public class SupervisorController {

    @Autowired
    private SupervisorService supervisorService;

    @PostMapping("/createFromSkppData")
    public ResponseEntity<?> createSupervisorFromSkppData(@RequestBody SupervisorSkppData supervisorSkppData) {
        SupervisorDTO supervisorDTO = supervisorService.createSupervisorFromSkppData(supervisorSkppData);
        return new ResponseEntity<>(supervisorDTO, HttpStatus.CREATED);
    }
}
