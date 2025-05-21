package com.example.demo.models.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfocomPassportData {
    private String pin;
    private String surname;
    private String name;
}
