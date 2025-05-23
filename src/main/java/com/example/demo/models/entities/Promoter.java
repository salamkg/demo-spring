package com.example.demo.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "promoters")
public class Promoter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "skpp_promoter_id")
    private Long skppPromoterId;

    @Column(name = "promoter_name")
    private String promoterName;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "msisdn")
    private String msisdn;

    @Column(name = "subs_id")
    private Long subsId;

    @Column(name = "inn")
    private String inn;

    @Column(name = "promoter_number")
    private Long promoterNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisor_id")
    @JsonBackReference
    private Supervisor supervisor;
}
