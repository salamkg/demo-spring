package com.example.demo.models.json;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupervisorSkppData {
    private Long id;
    private String name;
    private String dealer;
    private String msisdn;
    private Long subs;
}
