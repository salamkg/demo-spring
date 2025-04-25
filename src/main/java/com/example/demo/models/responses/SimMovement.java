package com.example.demo.models.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SimMovement {
    private int code;
    private String message;
}
