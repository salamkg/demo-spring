package com.example.demo.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassportInfoForMobile {
    private String fullName;
    private String birthDate;
    private String pin;
    private String passportSeriesAndNumber;
    private String passportAuthority;
    private String passportIssuedDate;
}
