package com.example.demo.repositories;

import com.example.demo.models.entities.PersonificationRequest;
import com.example.demo.models.enums.RequestStatus;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonificationRequestRepository extends JpaRepository<PersonificationRequest, Long> {


    @Query(value = "select count(personificationRequestId)" +
            "from PersonificationRequest " +
            "where subsId = :subsId " +
            "and status in (:statuses)")
    Integer countBySubsIdAndStatuses(@Param("subsId") Long subsId,
                                     @Param("statuses") List<RequestStatus> statuses);
}
