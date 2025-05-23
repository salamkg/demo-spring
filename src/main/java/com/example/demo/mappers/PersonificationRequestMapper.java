package com.example.demo.mappers;

import com.example.demo.models.dto.PersonificationRequestDTO;
import com.example.demo.models.entities.PersonificationRequest;
import org.springframework.stereotype.Component;

@Component
public class PersonificationRequestMapper {

    public PersonificationRequest toPersonificationRequest(PersonificationRequestDTO personificationRequestDTO) {
        PersonificationRequest.PersonificationRequestBuilder personificationRequestBuilder = PersonificationRequest.builder();
        // TODO this part not completed
        return personificationRequestBuilder
                .personificationRequestId(personificationRequestDTO.getPersonificationRequestId())
                .personificationRequesterUUId(personificationRequestDTO.getPersonificationRequestUUId())
                .subsId(personificationRequestDTO.getSubsId())
                .documentType(personificationRequestDTO.getDocumentType())
                .createDate(personificationRequestDTO.getCreatDate())
                .processDate(personificationRequestDTO.getProcessDate())
                .status(personificationRequestDTO.getStatus())
                .msisdn(personificationRequestDTO.getMsisdn())
                .build();
    }

    public PersonificationRequestDTO toPersonificationRequestDTO(PersonificationRequest savedPersonificationRequest) {
        // TODO this part not completed
        return null;
    }
}
