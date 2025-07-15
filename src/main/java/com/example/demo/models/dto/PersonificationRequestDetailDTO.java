package com.example.demo.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonificationRequestDetailDTO {
    private Long personificationRequestDetailId;
    private String pin;
    private String passportSeries;
    private String passportNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    private Date createDate;
    private String passportFront;
    private String passportBack;
    private String documentOwner;
    private String tundukDocument;
    private String signature;
    private String keyWord;
    private String email;
    private String promoterComment;
    private PersonificationRequestDTO personificationRequestDTO;
}
