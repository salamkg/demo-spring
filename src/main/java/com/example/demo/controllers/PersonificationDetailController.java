package com.example.demo.controllers;

import com.example.demo.models.dto.PersonificationRequestDTO;
import com.example.demo.models.dto.PersonificationRequestDetailDTO;
import com.example.demo.services.PersonificationDetailService;
import com.example.demo.services.PersonificationService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/api/personificationDetails")
public class PersonificationDetailController {

    @Autowired
    private PersonificationService personificationService;
    @Autowired
    private PersonificationDetailService personificationDetailService;

    @PostMapping("/create")
    public ResponseEntity<?> createPersonificationRequestDetail(@RequestParam Long personificationRequestId, @RequestParam String pin,
                                                                @RequestParam String passportSeries, @RequestParam String passportNumber,
                                                                @RequestPart MultipartFile docOwner, @RequestPart MultipartFile passFront,
                                                                @RequestPart MultipartFile passBack) {

        PersonificationRequestDTO personificationRequestDTO = personificationService.findById(personificationRequestId);
        PersonificationRequestDetailDTO personificationRequestDetailDTO =
                personificationDetailService.create(personificationRequestDTO, pin, passportSeries, passportNumber);
        personificationRequestDetailDTO = personificationDetailService.addFiles(personificationRequestDetailDTO, docOwner, passFront, passBack);

        return ResponseEntity.ok(personificationRequestDetailDTO);

    }

    @GetMapping("/findByPersonificationRequestId")
    public ResponseEntity<?> findDetailsByPersonificationRequestId(@RequestParam Long personificationRequestId) {
        PersonificationRequestDetailDTO personificationRequestDetailDTO = personificationDetailService.findByPersonificationRequestId(personificationRequestId);
        return ResponseEntity.ok(personificationRequestDetailDTO);
    }
}
