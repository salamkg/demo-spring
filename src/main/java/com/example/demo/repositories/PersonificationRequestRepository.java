package com.example.demo.repositories;

import com.example.demo.models.entities.PersonificationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonificationRequestRepository extends JpaRepository<PersonificationRequest, Long> {
}
