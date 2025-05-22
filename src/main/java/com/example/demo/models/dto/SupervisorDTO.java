package com.example.demo.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorDTO {
    private Long supervisorId;
    private String supervisorName;
    private String dealer;
    private Long skppSupervisorId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy HH:mm:ss")
    private Date createDate;
    private String msisdn;
    private Long subsId;
}
