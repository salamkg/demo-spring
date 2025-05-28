package com.example.demo.models.responses;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendPersonificationDataResponse {
    private String title;
    private String msisdn;
    private String message;
}
