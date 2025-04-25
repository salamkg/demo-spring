package com.example.demo.models.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MsisdnCheckResponse {
    private String message;
    private Long subsId;
}
