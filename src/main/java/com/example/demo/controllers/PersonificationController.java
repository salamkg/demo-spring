package com.example.demo.controllers;

import com.example.demo.models.responses.PersonificationRequestResponse;
import com.example.demo.services.PersonificationService;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api/personification")
public class PersonificationController {

    @Autowired
    private PersonificationService personificationService;

    @PostMapping("/createForIDCard")
    public ResponseEntity<PersonificationRequestResponse> createForIDCard(@RequestParam String token, @RequestParam String msisdn,
                                                          @RequestParam String pin, @RequestParam String firstName,
                                                          @RequestParam String passportSeries, @RequestParam String passportNumber,
                                                          @RequestParam MultipartFile documentOwner, @RequestParam MultipartFile passportFront,
                                                          @RequestParam MultipartFile passportBack, @RequestParam(required = false) List<String> childNumbers,
                                                          @RequestParam(required = false) Long groupId) {
        PersonificationRequestResponse response = personificationService.createForIDCard(
                token, msisdn, pin, firstName, passportSeries, passportNumber, documentOwner, passportFront, passportBack, childNumbers, groupId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/scan-passport")
    public ResponseEntity<?> scanPassport(@RequestParam("file") MultipartFile passportFile) {
        String res = personificationService.scanPassport(passportFile);
        System.out.println(res);
        return ResponseEntity.ok().build();
    }

//    @GetMapping
//    public ResponseEntity<?> checkMsisdn(@RequestParam String token, @RequestParam String msisdn) {
//        personificationService.checkMsisdn(token, msisdn);
//        return ResponseEntity.ok().build();
//    }

}
