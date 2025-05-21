package com.example.demo.models.dto;

import com.example.demo.models.enums.DocumentType;
import com.example.demo.models.enums.RequestStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonificationRequestDTO {

    private Long personificationRequestId;
    private String msisdn;
    private Long subsId;
    private DocumentType documentType;
    private UUID personificationRequestUUId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    private Date creatDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    private Date processDate;
    private Boolean connectedToInfocom;
    private Boolean connectedToPhotoMatch;
    private PromoterDTO promoterDTO;
    private RequestStatus status;
    private Date statusDate;
    private Boolean hasChildNumbers;
    private Boolean uploadedToEldocs;
    private String tempFilePath;
    private Boolean deletedFromTempDir;
}
