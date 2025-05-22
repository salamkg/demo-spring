package com.example.demo.dao;

import com.example.demo.models.entities.Promoter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromoterRepository extends JpaRepository<Promoter, Long> {
    Optional<Promoter> findPromoterBySkppPromoterId(Long skppPromoterId);

    Promoter findPromoterByMsisdn(String msisdn);
}
