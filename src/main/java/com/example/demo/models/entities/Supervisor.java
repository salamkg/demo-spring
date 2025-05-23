package com.example.demo.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "supervisors")
public class Supervisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supervisor_id;

    private String dealer;

    @Column(name = "skpp_supervisor_id")
    private Long skppSupervisorId;

    private String supervisor_name;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "msisdn")
    private String msisdn;

    @Column(name = "subs_id")
    private Long subsId;

    @OneToMany(mappedBy = "supervisor")
    @JsonManagedReference
    private List<Promoter> promoters;
}
