package com.example.demo.dao;

import com.example.demo.models.entities.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {

    Supervisor findByPromoters_Id(Long promoterId);
}
