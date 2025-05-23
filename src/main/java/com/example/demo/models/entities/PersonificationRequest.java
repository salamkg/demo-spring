package com.example.demo.models.entities;

import com.example.demo.models.enums.DocumentType;
import com.example.demo.models.enums.RequestStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personification_requests")
public class PersonificationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personificationRequestId;

    private String msisdn;

    private Long subsId;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    private UUID personificationRequesterUUId;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    @Column(name = "create_date")
    private Date createDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    @Column(name = "process_date")
    private Date processDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promoter_id")
    private Promoter promoter;

    @Enumerated(value = EnumType.STRING)
    private RequestStatus status;
}
