package com.example.demo.models.json;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubscriberProfile {
    private int type;
    private Long subsId;
    private String abonName;
    private String rtplId;
    private String rtplName;
    private String billGroup;
    private String activationDate;
    private String fullBalance;
    private String availBalance;
    private String language;
    private String errorMessage;
}
