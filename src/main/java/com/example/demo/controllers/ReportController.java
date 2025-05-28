package com.example.demo.controllers;

import com.example.demo.models.entities.sql.Order;
import com.example.demo.models.entities.sql.OrderItem;
import com.example.demo.models.entities.sql.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    private EntityManager em;

    @GetMapping
    public ResponseEntity<?> getData() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);
        Join<User, Order> order = user.join("orders");
        Join<User, OrderItem> items = order.join("orderItems");

        cq.select(user)
                .groupBy(user.get("id"))
                .having(cb.gt(cb.sum(cb.prod(items.get("quantity"), items.get("price"))), 1000));
        List<User> result = em.createQuery(cq).getResultList();

        return ResponseEntity.ok(result);
    }
}
