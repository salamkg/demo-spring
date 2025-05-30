package com.example.demo.controllers;

import com.example.demo.models.entities.sql.Order;
import com.example.demo.models.entities.sql.OrderItem;
import com.example.demo.models.entities.sql.User;
import com.example.demo.models.enums.RequestStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        // ---------------------------------------------------------------------------------------------------

        cq.select(user)
                .groupBy(user.get("id"))
                .having(cb.gt(cb.sum(cb.prod(items.get("quantity"), items.get("price"))), 1000));
        List<User> result = em.createQuery(cq).getResultList();
        // ---------------------------------------------------------------------------------------------------
        String appVersion = "11.23.4.5";
        int appVersionCode = Integer.parseInt(appVersion.replaceAll("[\\.]", ""));
        if (appVersionCode > 10) {
            Map<String, Boolean> map = new HashMap<>();
            map.put("ELCARD", true);
            map.put("VISA", true);
            System.out.println(map); // Result = {VISA=true, ELCARD=true}
        }
        System.out.println(appVersionCode);
        // ---------------------------------------------------------------------------------------------------
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appVersion", appVersion);
        paramMap.put("appVersionCode", String.valueOf(appVersionCode));
        paramMap.entrySet().stream().forEach(e -> {
            System.out.println(e.getKey() + "=" + e.getValue());
        });
        System.out.println("..........");
        if (Strings.isNotEmpty(paramMap.get("appVersion"))) {
            System.out.println(paramMap.get("appVersion"));
        }
        System.out.println("..........");
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        // ---------------------------------------------------------------------------------------------------
        String sql = "select * from orders";

        List<?> sqlList = em.createNativeQuery(sql).getResultList();
        System.out.println(sqlList);

        return ResponseEntity.ok(result);
    }
}
