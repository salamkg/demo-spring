package com.example.demo.models.json;

import jakarta.persistence.Id;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromoterSkppData {

    private Long id;
    private String name;
    private String msisdn;
    private Long subs;
    private String inn;
    private Long p_number; //Promoter number - promoter id to MegaPay
}
